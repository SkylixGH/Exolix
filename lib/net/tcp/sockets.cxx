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

    void SocketServer::onSocketInternal(u64 socketFd) {
        Socket socket;
        onAccept(socket);
    }

    void SocketServer::listen() {
        if (!address.isValidHost())
            throw SocketServerException(SocketServerErrors::INVALID_ADDRESS_HOST, "The host in the server address is invalid");

#if defined(__linux__) || defined(__APPLE__)
        try {
            unixCoreServer->listen(address.getProcessed(), address.port);
        } catch (UnixTcpServerException &e) {
            throw SocketServerException (
                SocketServerErrors::LISTEN_FAILED,
                "The Unix TCP server failed to listen due to the following error: " + e.message
            );
        }
#elif defined(_WIN32)
        try {
            winsockCoreServer->listen(address.getProcessed(), address.port);
        } catch (WinsockTcpServerException &e) {
            throw SocketServerException (
                SocketServerErrors::LISTEN_FAILED,
                "The Win32 TCP server failed to listen due to the following error: " + e.message
            );
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

    void SocketServer::setOnPendingListener(std::function<void(u64 socketFd)> listener) {
        onPending = listener;
    }
}
