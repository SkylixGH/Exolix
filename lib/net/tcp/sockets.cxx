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

    void SocketServer::onSocketInternal(uint64_t socketFd) {
        Socket socket;
        onAccept(socket);
    }

    void SocketServer::listen() {
        if (!address.isValidHost()) {} // TODO: Handle error
#if defined(__linux__) || defined(__APPLE__)
        try {
            unixCoreServer->listen(address.host, address.port);
        } catch (UnixTcpServerException &e) {
            
        }
#elif defined(_WIN32)
        try {
            winsockCoreServer->listen(address.host, address.port);
        } catch (WinsockTcpServerException &e) {
            
        }
#endif
    }

    void SocketServer::stop() {

    }

    void SocketServer::block() {

    }

    void SocketServer::setOnAcceptListener(std::function<void(Socket &socket)> listener) {
        onAccept = listener;
    }

    void SocketServer::setOnPendingListener(std::function<void(uint64_t socketFd)> listener) {
        onPending = listener;
    }
}
