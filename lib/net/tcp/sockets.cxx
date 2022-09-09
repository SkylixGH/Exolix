#include "sockets.hxx"
#include <cstring>
#include <utility>
#include <arpa/inet.h>

namespace exolix {
    SocketMessage::SocketMessage(const std::string& messageString) :
            size(messageString.length()) {
        data = new char[messageString.length() + 1];

#if defined(_WIN32)
        strcpy_s(data, messageString.length() + 1, messageString.c_str());
#elif defined(__linux__) || defined(__APPLE__)
        strcpy(data, messageString.c_str());
#endif
    }

    SocketMessage::SocketMessage(char buffer[], u16 length) :
            data(buffer),
            size(length) {
    }

    char *SocketMessage::getData() {
        return data;
    }

    std::string SocketMessage::toString() {
        return std::string { data, size };
    }

    Socket::Socket(u64 fd, std::optional<SSL *> ssl) :
            socketFd(fd), sslClient(ssl) {
        if (sslClient.has_value()) {
            tls = true;
        } else {
            tls = false;
        }

        thread = new std::thread([this]() {
            const int bufferSize = 65535;
            char buffer[bufferSize];
            int bytesReceived;

#if defined(__linux__) || defined(__APPLE__)
            struct sockaddr_in addr {};
            socklen_t addr_size = sizeof(struct sockaddr_in);
            getpeername((int) socketFd, (struct sockaddr *) &addr, &addr_size);

            clientIp = std::string(inet_ntoa(addr.sin_addr));
#elif defined(_WIN32)
            struct sockaddr_in addr;
            int addr_size = sizeof(struct sockaddr_in);
            getpeername(socketFd, (struct sockaddr *) &addr, &addr_size);

            clientIp = std::string(inet_ntoa(addr.sin_addr));
#endif

            if (!tls) {
                while (open) {
#if defined(__linux__) || defined(__APPLE__)
                    bytesReceived = (int) recv((int) socketFd, buffer, bufferSize, 0);

                    if (bytesReceived == 0) {
                        open = false;
                        break;
                    }

                    SocketMessage message(buffer, bytesReceived);
                    onReceive(message);
#elif defined(_WIN32)
                    bytesReceived = recv(socketFd, buffer, bufferSize, 0);

                    if (bytesReceived > 0) {
                        if (!open) {
                            break;
                        }

                        SocketMessage message(buffer, bytesReceived);
                        onReceive(message);

                        memset(buffer, 0, bufferSize);
                    } else {
                        open = false;
                        break;
                    }
#endif
                }
            } else {
                while (open) {
                    bytesReceived = SSL_read(sslClient.value(), buffer, bufferSize);

                    if (bytesReceived > 0) {
                        SocketMessage message(buffer, bytesReceived);
                        onReceive(message);

                        memset(buffer, 0, bufferSize);
                    } else {
                        open = false;
                        break;
                    }
                }
            }
        });
    }

    Socket::~Socket() {
        close();

        if (thread->joinable()) {
            thread->join();
        }

        delete thread;
    }

    void Socket::close() {
        open = false;

#if defined(__linux__) || defined(__APPLE__)
        ::close((int) socketFd);
#elif defined(_WIN32)
        closesocket(socketFd);
#endif
    }

    bool Socket::isOpen() const {
        return open;
    }

    void Socket::setOnReceiveListener(std::function<void(SocketMessage &)> listener) {
        onReceive = std::move(listener);
    }

    void Socket::block() {
        if (thread->joinable()) thread->join();
    }

    void Socket::send(exolix::SocketMessage messageRaw) {
        if (!open) return;

        if (tls) {
            SSL_write(sslClient.value(), messageRaw.getData(), messageRaw.size);
            return;
        }

#if defined(__linux__) || defined(__APPLE__)
        write((int) socketFd, messageRaw.getData(), messageRaw.size);
#elif defined(_WIN32)
        ::send(socketFd, messageRaw.getData(), messageRaw.size, 0);
#endif
    }

    void Socket::send(const std::string& messageString) {
        send(SocketMessage(messageString));
    }

    u64 Socket::getSocketFd() const {
        return socketFd;
    }

    std::string Socket::getClientIp() {
        return clientIp;
    }

    SocketServer::SocketServer(exolix::NetAddress &address) :
            address(address) {
#if defined(__linux__) || defined(__APPLE__)
        unixCoreServer = new UnixTcpServer([this](int socketFd) {
            onSocketInternal(socketFd);
        });
#elif defined(_WIN32)
        winsockCoreServer = new WinsockTcpServer([this](SOCKET socketFd) {
            onSocketInternal(socketFd);
        });
#endif
    }

    SocketServer::~SocketServer() {
#if defined(__linux__) || defined(__APPLE__)
        delete unixCoreServer;
#elif defined(_WIN32)
        delete winsockCoreServer;
#endif

        delete thread;
    }

    void SocketServer::onSocketInternal(u64 socketFd) {
        std::optional<SSL *> ssl = std::nullopt;

#if defined(__linux__) || defined(__APPLE__)
        // TODO: Impl
#elif defined(_WIN32)
        ssl = winsockCoreServer->getTlsClients()[socketFd];
#endif

        Socket socket(socketFd, ssl);
        onAccept(socket);
    }

    void SocketServer::listen() {
        if (!address.isValidHost())
            throw SocketServerException(SocketServerErrors::INVALID_ADDRESS_HOST,
                                        "The host in the server address is invalid");

        if (tls) {
#if defined(__linux__) || defined(__APPLE__)
            // TODO: Unix
#elif defined(_WIN32)
            winsockCoreServer->setTls(tls);

            winsockCoreServer->setCert(tlsConfiguration.cert);
            winsockCoreServer->setKey(tlsConfiguration.key);
#endif
        }

        thread = new std::thread([this]() {
#if defined(__linux__) || defined(__APPLE__)
            try {
                if (unixCoreServer != nullptr)
                    unixCoreServer->listen(address.getProcessed(), address.port);
            } catch (UnixTcpServerException &e) {
                throw SocketServerException(
                        SocketServerErrors::LISTEN_FAILED,
                        "The Unix TCP server failed to listen due to the following error: " + e.message
                );
            }
#elif defined(_WIN32)
            try {
                winsockCoreServer->listen(address.getProcessed(), address.port);
            } catch (WinsockTcpServerException &e) {
                throw SocketServerException(
                        SocketServerErrors::LISTEN_FAILED,
                        "The Win32 TCP server failed to listen due to the following error: " + e.message
                );
            }
#endif
        });
    }

    void SocketServer::stop() {
#if defined(__linux__) || defined(__APPLE__)
        if (unixCoreServer != nullptr)
            unixCoreServer->halt();
#elif defined(_WIN32)
        winsockCoreServer->halt();
#endif
    }

    void SocketServer::block() {
        if (thread != nullptr && thread->joinable()) thread->join();
    }

    void SocketServer::setOnAcceptListener(std::function<void(Socket &socket)> listener) {
        onAccept = std::move(listener);
    }

    void SocketServer::setTls(std::false_type tlsInput) {
        this->tls = tlsInput;
    }

    void SocketServer::setTls(exolix::SocketTlsConfiguration configuration) {
        this->tls = true;
        this->tlsConfiguration = configuration;
    }
}
