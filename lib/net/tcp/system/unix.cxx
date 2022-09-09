#include "unix.hxx"

#if defined(__linux__) || defined(__APPLE__)

#include <arpa/inet.h>
#include <iostream>
#include <unistd.h>
#include <cstring>
#include "../tlsManager.hxx"

#endif

namespace exolix {
#if defined(__linux__) || defined(__APPLE__)

    UnixTcpServer::UnixTcpServer(const std::function<void(int socketFd)> &connectionHandlerCallback) :
            connectionHandler(connectionHandlerCallback) {
    }

    void UnixTcpServer::setupSocket() {
        socketFd = socket(AF_INET, SOCK_STREAM, 0);

        if (socketFd < 0)
            switch (errno) {
                case EACCES:
                    throw UnixTcpServerException(
                            UnixTcpServerErrors::ADDRESS_PROTECTED_PERM_DENIED,
                            "Could not create socket interface for socket server due to an access and or permission error"
                    );

                default:
                    throw UnixTcpServerException(
                            UnixTcpServerErrors::OTHER_SYS_ERROR,
                            "Could not create socket interface for socket server due to an unknown error: " +
                            std::string(strerror(errno))
                    );
            }
    }

    void UnixTcpServer::setupAddress(u16 port, const std::string &host) {
        serverAddress.sin_family = AF_INET;
        serverAddress.sin_addr.s_addr = inet_addr(host.c_str());
        serverAddress.sin_port = htons(port);
    }

    void UnixTcpServer::setupBinding() {
        int bindResult = bind(socketFd, (struct sockaddr *) &serverAddress, sizeof(serverAddress));

        if (bindResult < 0)
            switch (errno) {
                case EROFS:
                case EACCES:
                    throw UnixTcpServerException(
                            UnixTcpServerErrors::ADDRESS_PROTECTED_PERM_DENIED,
                            "Could not bind socket server to address due to access and or permission error"
                    );

                case EADDRINUSE:
                case EINVAL:
                    throw UnixTcpServerException(
                            UnixTcpServerErrors::ADDRESS_IN_USE,
                            "Could not bind socket server to address due to the address already being in use"
                    );

                case EADDRNOTAVAIL:
                    throw UnixTcpServerException(
                            UnixTcpServerErrors::ADDRESS_INVALID,
                            "Could not bind socket server to address due to the address not being available"
                    );
            }
    }

    UnixTcpServer::~UnixTcpServer() {
        halt();
    }

    void UnixTcpServer::listen(const std::string &address, u16 port) {
        if (tlsOn)
            TlsManager::safeInitialize();

        setupSocket();
        setupAddress(port, address);
        setupBinding();

        int result = ::listen(socketFd, 5);
        if (result < 0) {
            exit(6793);
        }

        if (tlsOn) {
            sslCtx = SSL_CTX_new(SSLv23_server_method());
            SSL_CTX_set_options(sslCtx, SSL_OP_SINGLE_DH_USE);

            int cert = SSL_CTX_use_certificate_file(sslCtx, tlsCert.c_str(), SSL_FILETYPE_PEM);
            int key = SSL_CTX_use_PrivateKey_file(sslCtx, tlsKey.c_str(), SSL_FILETYPE_PEM);

            if (cert != 1)
                throw UnixTcpServerException(
                        UnixTcpServerErrors::SSL_CERTIFICATE_LD_ERROR,
                        "Failed to load the SSL certificate due to the following error code: " + std::to_string(cert)
                );

            if (key != 1)
                throw UnixTcpServerException(
                        UnixTcpServerErrors::SSL_KEY_LD_ERROR,
                        "Failed to load the SSL key due to the following error code: " + std::to_string(key)
                );
        }

        connectable = true;
        bool pending = false;

        while (connectable) {
            clientLen = sizeof(clientAddress);
            clientFd = accept(socketFd, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLen);

            if (!(clientFd < 0)) {
                pending = true;

                auto *thread = new std::thread([this, &pending] () {
                    SSL *ssl = nullptr;

                    if (tlsOn) {
                        ssl = SSL_new(sslCtx);
                        SSL_set_fd(ssl, clientFd);

                        int acceptResult = SSL_accept(ssl); // TODO: Handle
                        clientSslGroup[clientFd] = ssl;
                    }

                    pending = false;
                    connectionHandler(clientFd);

                    if (tlsOn) {
                        SSL_shutdown(ssl);
                        SSL_free(ssl);

                        clientSslGroup.erase(clientFd);
                    }
                });

                while (pending) {}
                clientThreads.insert(std::pair<int, std::thread *>(clientFd, thread));
            }
        }
    }

    void UnixTcpServer::halt() {
        connectable = false;
        close(socketFd);

        if (sslCtx != nullptr)
            SSL_CTX_free(sslCtx);

        for (auto &clientThread : clientThreads) {
            close(clientThread.first);
            clientThread.second->join();

            delete clientThread.second;
        }

        for (auto &clientSsl : clientSslGroup) {
            SSL_shutdown(clientSsl.second);
            SSL_free(clientSsl.second);
        }

        clientThreads.clear();
    }

    void UnixTcpServer::close(int socketFd) {
        try {
            ::close(socketFd);
        } catch (...) {
            // ignore
        }
    }

    void UnixTcpServer::send(int socketFd, char buffer[], u16 length) {
        ::send(socketFd, buffer, length, 0);
    }

    void UnixTcpServer::cleanThread(int socketFdNotThis) {
        try {
            std::thread *thread = clientThreads.at(socketFdNotThis);

            if (thread != nullptr) {
                if (thread->joinable()) {
                    thread->join();
                }

                delete thread;
            }
        } catch (...) {
            // ignore
        }
    }

    std::optional<std::thread *> UnixTcpServer::getThread(int socketFdNotThis) {
        if (clientThreads.find(socketFdNotThis) != clientThreads.end())
            return clientThreads.at(socketFdNotThis);

        return std::nullopt;
    }

    size_t UnixTcpServer::countThreads() {
        return clientThreads.size();
    }

    void UnixTcpServer::setCert(std::string certSsl) {
        tlsCert = certSsl;
    }

    void UnixTcpServer::setKey(std::string keySsl) {
        tlsKey = keySsl;
    }

    void UnixTcpServer::setTls(bool tls) {
        tlsOn = tls;
    }

    std::map<int, SSL *> UnixTcpServer::getSslGroup() {
        return clientSslGroup;
    }
#endif
}
