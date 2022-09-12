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

#endif

#include <iostream>

namespace exolix {
    SocketServerAdapter::SocketServerAdapter(exolix::i64 socketFd, std::optional<SSL *> ssl):
        cSsl(ssl), cSocket(socketFd) {
    }

    SocketServer::SocketServer(exolix::NetAddress address, int backlog) :
            address(std::move(address)), backlog(backlog) {
    }

    SocketServer::~SocketServer() {
        if (serverThread != nullptr)
            serverThread->block();

        delete serverThread;
    }

    SocketServerErrors SocketServer::setTLS(bool enabled) {
        if (online || busy)
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

        if (address.hasErrors())
            return SocketServerErrors::FaultyAddress;

        busy = true;
        SocketServerErrors resDat = SocketServerErrors::Ok;

        Thread worker([this, &resDat] () {
            InternetVersion ipVersion;

            address.getInternetVersion(ipVersion);

#if defined(__linux__) || defined(__APPLE__)
            int extSocketFd;
            int clientLength;

            int bytesReceived;

            std::string hostname;
            u16 portNumber;

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

            auto addressGetHostnameError = address.getHostname(hostname);
            if (addressGetHostnameError != NetAddressErrors::Ok) {
                // TODO: Handle
            }

            const auto hostNameCString = hostname.c_str();

            auto addressGetPortError = address.getPort(portNumber);
            if (addressGetPortError != NetAddressErrors::Ok) {
                // TODO: Hande
            }

            if (ipVersion == InternetVersion::Ipv4) {
                hostEntry = gethostbyname2(hostNameCString, versionFamily);
                if (hostEntry == nullptr) {
                    // TODO: Handle
                }

                memcpy(&serverAddress.sin_addr, hostEntry->h_addr_list[0], hostEntry->h_length);

                serverAddress.sin_family = versionFamily;
                serverAddress.sin_port = htons(portNumber);
            } else if (ipVersion == InternetVersion::Ipv6) {
                hostEntry = gethostbyname2(hostNameCString, versionFamily);

                if (hostEntry == nullptr) {
                    // TODO: Handle
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

            serverThread = new Thread([this, &buffer, &clientLength, &extSocketFd, &clientAddress] () {
                while (online) {
                    clientLength = sizeof(clientAddress);
                    extSocketFd = accept((int) socketFd, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLength);

                    if (extSocketFd < 0) {
                        // TODO: Handle
                    } else {

                    }
                }

                delete[] buffer;
            });
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
    }

    SocketServerErrors SocketServer::setReceiveBufferSize(u16 size) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        receiveBufferSize = size;
        return SocketServerErrors::Ok;
    }

    void SocketServer::cleanUp() {
        if (socketFd != -1) {
            close((int) socketFd);
            socketFd = -1;
        }

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
    }
}
