#include "tcpip.h"
#include <utility>

#if defined(__linux__) || defined(__APPLE__)

    #include <netdb.h>
    #include <netinet/in.h>
    #include <strings.h>
    #include <cstring>
    #include <openssl/err.h>
    #include <arpa/inet.h>
    #include <unistd.h>

#elif defined(_WIN32)

    #include <windows.h>
    #include <winsock2.h>
    #include <ws2tcpip.h>
    #include <string>
    #include <process.h>

#endif

namespace exolix {
    SocketServerAdapter::SocketServerAdapter(u16 socketFd, std::optional<SSL *> ssl, char *readBuffer, u16 readBufferSize) :
        cSsl(ssl), cSocket(socketFd), bufferWriteSource(readBuffer), bufferWriteSourceSize(readBufferSize),
        cConnected(false), win32ThreadBlocked(false), waitingSocketJob(false), win32ThreadOver(false) {

#if defined(_WIN32)
        waitingSocketJob = true;
        unsigned threadId;

        cConnected = true;
        cThreadWin32 = (HANDLE) _beginthreadex(
                nullptr, 0,
                [] (void *arg) -> unsigned {
                    auto *self = (SocketServerAdapter *) arg;
                    auto *buffer = self->bufferWriteSource;
                    auto bufferSize = self->bufferWriteSourceSize;

                    int readData;

                    self->waitingSocketJob = false;

                    while (self->cConnected) {
                        readData = recv(self->cSocket, buffer, bufferSize, 0);

                        if (readData <= 0) {
                            break;
                        }

                        std::string data(buffer, readData);
                        printf("Received: %s\n", data.c_str());
                    }

                    return 0;
                },
                (void *) this, 0, &threadId
        );
#elif defined(__linux__) || defined(__APPLE__)
        cConnected = true;
        cThread = new Thread([this] () {
            while (cConnected) {
                #if defined(__linux__) || defined(__APPLE__)
                // TODO: Implement while body Linux Mac
                #elif _WIN32

                #endif
            }

            if (cSsl.has_value()) {
                SSL_shutdown(cSsl.value());
                SSL_free(cSsl.value());
            }

            kill();
        });
#endif
    }

    SocketServerAdapter::~SocketServerAdapter() {
        delete cThread;

#ifdef _WIN32
        CloseHandle(cThreadWin32);
#endif

        if (cConnected) {
            kill();
        }

        if (cSsl.has_value()) {
            SSL_shutdown(cSsl.value());
            SSL_free(cSsl.value());
        }

        kill();
    }

    bool SocketServerAdapter::isActive() const {
#if defined(__linux__) || defined(__APPLE__)
        return cConnected && cThread->isActive();
#elif defined(_WIN32)
        return cConnected && !win32ThreadOver;
#endif
    }

    bool SocketServerAdapter::blockedBefore() {
#if defined(__linux__) || defined(__APPLE__)
        // TODO: WOrk
#elif defined(_WIN32)
        return win32ThreadBlocked;
#endif
    }

    void SocketServerAdapter::kill() {
        if (cConnected) {
            cConnected = false;

            #if defined(__linux__) || defined(__APPLE__)

                close(cSocket);

            #elif defined(_WIN32)

                closesocket(cSocket);

            #endif
        }
    }

    SocketServerAdapterErrors SocketServerAdapter::block() {
        if (cConnected) {
#if defined(__linux__) || defined(__APPLE__)
            auto threadBlockResult = cThread->block();

            switch (threadBlockResult) {
                case ThreadErrors::Ok:
                    return SocketServerAdapterErrors::Ok;

                case ThreadErrors::ThreadAlreadyBlocked:
                    return SocketServerAdapterErrors::SocketAlreadyBlocked;

                case ThreadErrors::ThreadNotActive:
                    return SocketServerAdapterErrors::SocketNotAlive;
            }
#elif defined(_WIN32)
            if (!win32ThreadBlocked) {
                win32ThreadBlocked = true;

                DWORD blockResultWin32 = WaitForSingleObject(cThreadWin32, INFINITE);
                win32ThreadOver = true;

                return SocketServerAdapterErrors::Ok;
            }

            return SocketServerAdapterErrors::SocketAlreadyBlocked;
#endif
        }

        return SocketServerAdapterErrors::SocketNotAlive;
    }

    SocketServer::SocketServer(exolix::NetAddress address, int backlog) :
        address(std::move(address)), backlog(backlog), busy(false), online(false),
        tls(false), serverThread(nullptr), socketFd(-1), receiveBufferSize(1024) {
    }

    SocketServer::~SocketServer() {
        delete serverThread;
    }

    SocketServerErrors SocketServer::setTLS(bool enabled) {
        if (busy || online)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        tls = enabled;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::setCertificate(const std::string &path) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        if (!tls)
            return SocketServerErrors::TlsNotEnabled;

        certificate = path;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::setPrivateKey(const std::string &path) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        if (!tls)
            return SocketServerErrors::TlsNotEnabled;

        privateKey = path;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::load() {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        busy = true;
        SocketServerErrors resDat = SocketServerErrors::Ok;

        Thread worker([this, &resDat] () {
            InternetVersion ipVersion;
            NetAddressErrors addressError;

            addressError = address.getInternetVersion(ipVersion);
            if (addressError != NetAddressErrors::Ok) {
                resDat = SocketServerErrors::AddressError;
                cleanUp();

                return;
            }

            std::string hostname;
            u16 portNumber;

            addressError = address.getHostname(hostname);
            if (addressError != NetAddressErrors::Ok) {
                resDat = SocketServerErrors::FaultyAddressHostname;
                cleanUp();

                return;
            }

            addressError = address.getPort(portNumber);
            if (addressError != NetAddressErrors::Ok) {
                resDat = SocketServerErrors::FaultyAddressPort;
                cleanUp();

                return;
            }

#if defined(__linux__) || defined(__APPLE__)
            int extSocketFd;
            int clientLength;

            int bytesReceived;

            struct sockaddr_in serverAddress {};
            struct sockaddr_in clientAddress {};

            struct sockaddr_in6 serverAddress6 {};
            struct sockaddr_in6 clientAddress6 {};

            char *buffer = new char[receiveBufferSize];

            int versionFamily;
            int result;
            int flag = 1;

            switch (ipVersion) {
                case InternetVersion::Ipv4:
                    versionFamily = AF_INET;
                    break;

                case InternetVersion::Ipv6:
                    versionFamily = AF_INET6;
                    break;

                case InternetVersion::Unknown:
                    break;
            }

            socketFd = socket(versionFamily, SOCK_STREAM, 0);
            if (socketFd < 0) {
                switch (errno) {
                    case EACCES:
                        resDat = SocketServerErrors::PermissionFaulty;
                        break;

                    case EAFNOSUPPORT:
                    case EPROTONOSUPPORT:
                        resDat = SocketServerErrors::IpVersionNotSupported;
                        break;

                    case EMFILE:
                    case ENFILE:
                        resDat = SocketServerErrors::TooManyDescriptorsOpen;
                        break;

                    case ENOBUFS:
                    case ENOMEM:
                        resDat = SocketServerErrors::FaultyMemoryAccess;
                        break;

                    default:
                        resDat = SocketServerErrors::CouldNotCreateServerSocketInstance;
                        break;
                }

                cleanUp();
                return;
            }

            result = setsockopt((int) socketFd, SOL_SOCKET, SO_REUSEADDR, &flag, sizeof(flag));
            if (result < 0) {
                resDat = SocketServerErrors::CouldNotSetSocketOption;

                cleanUp();
                return;
            }

            bzero((char *) &serverAddress, sizeof(serverAddress));

            struct hostent *hostEntry {};

            const auto hostNameCString = hostname.c_str();

            if (ipVersion == InternetVersion::Ipv4) {
                hostEntry = gethostbyname2(hostNameCString, versionFamily);

                if (hostEntry == nullptr) {
                    resDat = SocketServerErrors::CouldNotResolveHostname;
                    cleanUp();

                    return;
                }

                memcpy(&serverAddress.sin_addr, hostEntry->h_addr_list[0], hostEntry->h_length);

                serverAddress.sin_family = versionFamily;
                serverAddress.sin_port = htons(portNumber);
            } else if (ipVersion == InternetVersion::Ipv6) {
                hostEntry = gethostbyname2(hostNameCString, versionFamily);

                if (hostEntry == nullptr) {
                    resDat = SocketServerErrors::CouldNotResolveHostname;
                    cleanUp();

                    return;
                }

                memcpy(&serverAddress6.sin6_addr, hostEntry->h_addr_list[0], hostEntry->h_length);

                serverAddress6.sin6_family = versionFamily;
                serverAddress6.sin6_port = htons(portNumber);
            }

            if (versionFamily == AF_INET6)
                result = bind((int) socketFd, (struct sockaddr *) &serverAddress6, sizeof(serverAddress6));
            else if (versionFamily == AF_INET)
                result = bind((int) socketFd, (struct sockaddr *) &serverAddress, sizeof(serverAddress));

            if (result < 0) {
                printf("Error binding socket %s\n", strerror(errno));
                // TODO: Handle
            }

            result = listen((int) socketFd, backlog);

            if (result < 0) {
                printf("Listen error\n");
                // TODO: Handle
            }

            online = true;
            busy = false;

            serverThread = new Thread([this, &buffer, &clientLength, &extSocketFd, &clientAddress, &bytesReceived] () {
                while (online) {
                    clientLength = sizeof(clientAddress);
                    extSocketFd = accept((int) socketFd, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLength);

                    if (extSocketFd < 0) {
                        // TODO: Handle
                    } else {
                        // receive data
                        bzero(buffer, receiveBufferSize);

                        while (true) {
                            bytesReceived = recv(extSocketFd, buffer, receiveBufferSize, 0);

                            if (bytesReceived == 0) {
                                break;
                            }

                            if (bytesReceived < 0) {
                                // TODO: Handle
                            }

                            if (bytesReceived > 0) {
                                //print
                                printf("%s\n", buffer);
                            }
                        }
                    }
                }

                delete[] buffer;
            });
#elif _WIN32
            auto extSocket = INVALID_SOCKET;
            WSADATA wsaData;

            int inBytes;
            int result;

            char *buffer = new char[receiveBufferSize];

            struct addrinfo *resultAddress {};
            struct addrinfo hints {};

            result = WSAStartup(MAKEWORD(2, 2), &wsaData);
            // TODO: Handle

            ZeroMemory(&hints, sizeof(hints));

            int versionFamily;

            switch (ipVersion) {
                case InternetVersion::Ipv4:
                    versionFamily = AF_INET;
                    break;

                case InternetVersion::Ipv6:
                    versionFamily = AF_INET6;
                    break;

                case InternetVersion::Unknown:
                    break;
            }

            hints.ai_family = versionFamily;
            hints.ai_socktype = SOCK_STREAM;
            hints.ai_protocol = IPPROTO_TCP;
            hints.ai_flags = AI_PASSIVE;

            result = getaddrinfo(hostname.c_str(), std::to_string(portNumber).c_str(), &hints, &resultAddress);
            // TODO: Handle

            socketFd = (i64) socket(resultAddress->ai_family, resultAddress->ai_socktype, resultAddress->ai_protocol);
            // TODO: Handle

            result = bind((SOCKET) socketFd, resultAddress->ai_addr, (int) resultAddress->ai_addrlen);
            // TODO: Handle

            freeaddrinfo(resultAddress);

            result = listen((SOCKET) socketFd, backlog);
            // TODO: Handle

            online = true;
            busy = false;

            while (online) {
                extSocket = accept((SOCKET) socketFd, nullptr, nullptr);

                if (extSocket != INVALID_SOCKET) {
                    auto *thread = new Thread([this, &extSocket, &buffer] () {
                        SocketServerAdapter adapter((i64) extSocket, std::nullopt, buffer, receiveBufferSize);

                        clients.insert({
                               extSocket,
                               adapter
                        });

                        onSocket(adapter);
                    });

                    clientThreads[(i64) extSocket] = thread;
                }
            }

            delete[] buffer;
#endif
        });

        worker.block();
        busy = false;

        return resDat;
    }

    SocketServerErrors SocketServer::unload() {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        busy = true;
        cleanUp();

        return SocketServerErrors::Ok;
    }

    bool SocketServer::isOnline() const {
        return online;
    }

    bool SocketServer::isBusy() const {
        return busy;
    }

    SocketServerErrors SocketServer::block() {
        if (serverThread == nullptr || !serverThread->isActive())
            return SocketServerErrors::ServerNotReadyForAction;

        auto blockRes = serverThread->block();

        switch (blockRes) {
            case ThreadErrors::ThreadNotActive:
                return SocketServerErrors::ServerNotReadyForAction;

            case ThreadErrors::ThreadAlreadyBlocked:
                return SocketServerErrors::CannotBlockServerAfterPreviouslyBlockedWithoutRestart;

            case ThreadErrors::Ok:
                return SocketServerErrors::Ok;
        }

        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::setReceiveBufferSize(u16 size) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        receiveBufferSize = size;
        return SocketServerErrors::Ok;
    }

    void SocketServer::cleanUp() {
#if defined(__linux__) || defined(__APPLE__)
        if (socketFd != -1) {
            close((int) socketFd);
            socketFd = -1;
        }
#elif defined(_WIN32)
        if ((SOCKET) socketFd != INVALID_SOCKET) {
            closesocket(socketFd);
            socketFd = (i64) INVALID_SOCKET;
        }
#endif

        if (serverThread != nullptr) {
            if (serverThread->isActive())
                serverThread->block();

            delete serverThread;
        }

        if (sslContext != std::nullopt) {
            SSL_CTX_free(sslContext.value());
            sslContext = nullptr;
        }

        online = false;
        busy = false;
    }

    void SocketServer::setOnSocketListener(const std::function<void(SocketServerAdapter &)> &listener) {
        onSocket = listener;
    }

    void SocketServer::collectGarbage() {
        for (auto &clientThread : clientThreads) {
            auto client = clients.find(clientThread.first);
            auto thread = clientThread.second;

            if (thread->blockedBefore() && !client->second.isActive()) {
                clients.erase(client);
                clientThreads.erase(clientThread.first);
                delete thread;
            }
        }

        printf("[GC] Collected %llu clients\n", clients.size());
    }
}
