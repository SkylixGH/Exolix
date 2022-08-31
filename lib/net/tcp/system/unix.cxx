#include "unix.hxx"

#if defined(__linux__) || defined(__APPLE__)
#include <arpa/inet.h>
#include <map>
#include <unistd.h>
#endif

namespace exolix {
#if defined(__linux__) || defined(__APPLE__)
    UnixTcpServer::UnixTcpServer(const std::function<void(int socketFd)> &connectionHandlerCallback):
        connectionHandler(connectionHandlerCallback) {
    }

    void UnixTcpServer::setupSocket() {
        socketFd = socket(AF_INET, SOCK_STREAM, 0);

        if (socketFd < 0) {
            exit(6793);
        }
    }

    void UnixTcpServer::setupAddress(uint16_t port, const std::string &host) {
        serverAddress.sin_family = AF_INET;
        serverAddress.sin_addr.s_addr = inet_addr(host.c_str());
        serverAddress.sin_port = htons(port);
    }

    void UnixTcpServer::setupBinding() {
        if (bind(socketFd, (struct sockaddr *) &serverAddress, sizeof(serverAddress)) < 0) {
            exit(67932);
        }
    }

    UnixTcpServer::~UnixTcpServer() {

    }

    void UnixTcpServer::listen(const std::string &address, uint16_t port) {
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
                connectionHandler(clientFd);
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

    void UnixTcpServer::send(int socketFd, char buffer[], uint16_t length) {
        if (handlerThreads.find(socketFd) != handlerThreads.end()) {
            write(socketFd, buffer, length);
        }
    }

    int UnixTcpServer::getActiveSockets() {
        return handlerThreads.size();
    }
#endif
}
