#include "sockets.hxx"

namespace exolix {
    SocketMessage::SocketMessage(std::string messageString):
        size(messageString.length()) {
        data = new char[messageString.length() + 1];

        strcpy_s(data, messageString.length() + 1, messageString.c_str());
    }

    SocketMessage::SocketMessage(char buffer[], u16 length):
        data(buffer),
        size(length) {
    }

    char *SocketMessage::getData() {
        return data;
    }

    std::string SocketMessage::toString() {
        return std::string(data, size);
    }

    Socket::Socket(u64 fd):
        socketFd(fd) {
        
        thread = new std::thread([this] () {
            const int bufferSize = 65535;
            char buffer[bufferSize];
            int bytesReceived = 0;

#if defined(__linux__) || defined(__APPLE__)
            clientIp = getIp(socketFd);
#elif defined(_WIN32)
//            clientIp = getIp(socketFd, bufferSize, buffer);
#endif

            while (open) {
                #if defined(__linux__) || defined(__APPLE__)
                    // TODO: Implement
                #elif defined(_WIN32)
                    bytesReceived = recv(socketFd, buffer, bufferSize, 0);

                    if (bytesReceived > 0) {
                        SocketMessage message(buffer, bytesReceived);
                        onReceive(message);

                        memset(buffer, 0, bufferSize);
                    } else {
                        open = false;
                        break;
                    }
                #endif
            }
        });
    }

    Socket::~Socket() {
        close();
        delete thread;
    }

    void Socket::close() {
        open = false;

#if defined(__linux__) || defined(__APPLE__)
        // TODO: Finish
#elif defined(_WIN32)
            closesocket(socketFd);
#endif
    }

    bool Socket::isOpen() {
        return open;
    }

    void Socket::setOnReceiveListener(std::function<void(SocketMessage &)> listener) {
        onReceive = listener;
    }

    void Socket::block() {
        if (thread->joinable()) thread->join();
    }

    void Socket::send(exolix::SocketMessage messageRaw) {
        if (!open) return;

#if defined(__linux__) || defined(__APPLE__)
        write(socketFd, messageRaw.data, messageRaw.size);
#elif defined(_WIN32)
        ::send(socketFd, messageRaw.getData(), messageRaw.size, 0);
#endif
    }

    void Socket::send(std::string messageString) {
        send(SocketMessage(messageString));
    }

    u64 Socket::getSocketFd() {
        return socketFd;
    }

    SocketServer::SocketServer(exolix::NetAddress &address) :
            address(address) {
#if defined(__linux__) || defined(__APPLE__)
        unixCoreServer = new UnixTcpServer([this](int socketFd) {
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
        Socket socket(socketFd);
        onAccept(socket);
    }

    void SocketServer::listen() {
        if (!address.isValidHost())
            throw SocketServerException(SocketServerErrors::INVALID_ADDRESS_HOST,
                                        "The host in the server address is invalid");

#if defined(__linux__) || defined(__APPLE__)
        try {
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
}
