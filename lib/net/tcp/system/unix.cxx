#include "unix.hxx"

#if defined(__linux__) || defined(__APPLE__)

#include <arpa/inet.h>
#include <iostream>
#include <unistd.h>
#include <cstring>

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
        // TODO: Work
    }

    void UnixTcpServer::listen(const std::string &address, u16 port) {
        setupSocket();
        setupAddress(port, address);
        setupBinding();

        int result = ::listen(socketFd, 5);
        if (result < 0) {
            exit(6793);
        }

        connectable = true;

        while (connectable) {
            clientLen = sizeof(clientAddress);
            clientFd = accept(socketFd, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLen);

            if (!(clientFd < 0)) {
                auto *thread = new std::thread([this] () {
                    connectionHandler(clientFd);
                });

                clientThreads.insert(std::pair<int, std::thread *>(clientFd, thread));
            }
        }
    }

    void UnixTcpServer::halt() {
        connectable = false;
        close(socketFd);
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
#endif
}
