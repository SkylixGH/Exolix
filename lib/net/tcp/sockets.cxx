#include "sockets.hxx"

namespace exolix {
    SocketServer::SocketServer(exolix::NetAddress &address):
        address(address) {
#if defined(__linux__) || defined(__APPLE__)
        unixCoreServer = new UnixTcpServer([this] (int socketFd) {
            onSocketInternal(socketFd);
        });
#elif defined(_WIN32)
        winsockCoreServer = new WinsockTcpServer([this] (SOCKET socketFd) {
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
    }

    void SocketServer::listen() {
        if (!address.isValidHost()) {} // TODO: Handle error
#if defined(__linux__) || defined(__APPLE__)
        // TODO: Add try catch around this
        unixCoreServer->listen(address.host, address.port);
#elif defined(_WIN32)
        // TODO: Add try catch around this
        winsockCoreServer->listen(address.host, address.port);
#endif
    }

    void SocketServer::stop() {

    }

    void SocketServer::block() {

    }

    void SocketServer::setOnAcceptListener(std::function<void(Socket &socket)> listener) {
        onAccept = listener;
    }

    void SocketServer::setOnPendingListener(std::function<void(int socketFd)> listener) {
        onPending = listener;
    }
}
