#include "sockets.h"

#if defined(__linux__) || defined(__APPLE__)

#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#elif _WIN32

#include <winsock2.h>

#endif

namespace exolix::net {
    // SocketMessage
    std::string SocketMessage::toString() const {
        int index;
        std::string string;

        for (index = 0; index < size; index++) {
            string += data[index];
        }

        return string;
    }

    // Socket
    Socket::Socket(int osHandle) : socketHandle(osHandle) {
    }

    void Socket::block() {
        if (thread.joinable()) thread.join();
    }

    void Socket::close() {
        running = false;
    }

    void Socket::send(const std::string &message) {

    }

    void Socket::send(const exolix::net::SocketMessage &message) {

    }

    // SocketServer
    SocketServer::SocketServer(const exolix::net::SocketServerOptions &options):
        options(options) {
    }

    SocketServer::~SocketServer() {
        shutdown();
        block();

        delete thread;
    }

    void SocketServer::listen(uint16_t listeningPort, const std::string &listeningHost) {
        if (hasStartedBefore)
            throw SocketError(SocketErrors::ServerPreviouslyStartedBefore, "Cannot start the server again because it was started before.");

        hasStartedBefore = true;

        this->port = listeningPort;
        this->host = listeningHost;

        struct sockaddr_in serverAddress {};

#if defined(__linux__) || defined(__APPLE__)
        const int addressLength = sizeof(serverAddress);
        const int option = 1;

        if ((osSocketHandle = socket(AF_INET, SOCK_STREAM, 0)) < 0)
            throw SocketError(SocketErrors::ServerCannotCreateSocket, "The OS could not create the socket.");

        if (setsockopt(osSocketHandle, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &option, sizeof(option)))
            throw SocketError(SocketErrors::ServerCannotSetSocketOptions, "The OS could not set the socket options.");

        serverAddress.sin_family = AF_INET;
        serverAddress.sin_port = htons(port);

        if (inet_pton(AF_INET, host.c_str(), &serverAddress.sin_addr) <= 0)
            throw SocketError(SocketErrors::ServerInvalidHost, "The host is invalid.");

        if (bind(osSocketHandle, (struct sockaddr *) &serverAddress, addressLength) < 0)
            throw SocketError(SocketErrors::ServerCannotBind, "The OS could not bind the socket.");

        if (::listen(osSocketHandle, options.backlog) < 0)
            throw SocketError(SocketErrors::ServerCannotListen, "The OS could not listen on the socket.");

        isListening = true;
        thread = new std::thread([this, &serverAddress, &addressLength] () {
            while (isListening) {
                int clientSocketHandle;

                if ((clientSocketHandle = accept(osSocketHandle, (struct sockaddr *) &serverAddress, (socklen_t *) &addressLength)) >= 0) {
                    Socket socket(clientSocketHandle);
                    sockets.insert({ clientSocketHandle, socket });

                    std::thread([this, &socket] () {
                        onSocketOpen(socket);
                    }).detach();
                } else {
                    std::cout << "Error: " << errno << std::endl;
                }
            }
        });
#elif _WIN32

#endif
    }

    void SocketServer::shutdown() {
        if (hasShutdownBefore)
            throw SocketError(SocketErrors::ServerPreviouslyStartedBefore, "Cannot shutdown the server again because it was shutdown before.");

        if (!isListening)
            throw SocketError(SocketErrors::ServerIsNotOnlineYet, "The server is not online yet.");

        isListening = false;
        hasShutdownBefore = true;

        for (auto &socket : sockets) {
            socket.second.close();
        }

        sockets.clear();

#if defined(__linux__) || defined(__APPLE__)
        close(osSocketHandle);
#elif _WIN32

#endif
    }

    void SocketServer::block() {
        if (thread != nullptr && thread->joinable()) thread->join();
    }

    void SocketServer::setOnSocketOpenListener(const std::function<void(Socket &)> &listener) {
        onSocketOpen = listener;
    }
}
