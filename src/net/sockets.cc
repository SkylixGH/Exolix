#include "sockets.h"
#include <sstream>

#if defined(__linux__) || defined(__APPLE__)

#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#elif _WIN32

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>

#endif

namespace exolix::net {
    // SocketMessage
    std::string SocketMessage::toString() const {
        std::stringstream ss;
        ss << data;
        return ss.str();
    }

    // Socket
    Socket::Socket(int osHandle) : socketHandle(osHandle) {
        thread = new std::thread([this] () {
            while (running) {
                char buffer[1024];

#if defined(__linux__) || defined(__APPLE__)
                long bytesRead = read(socketHandle, buffer, sizeof(buffer));
#elif _WIN32
                long bytesRead = recv(socketHandle, buffer, sizeof(buffer), 0);
#endif

                if (bytesRead < 0) {
                    close();
                    break;
                }

                SocketMessage message {
                    buffer,
                    sizeof(buffer)
                };

                onMessage(message);
            }
        });
    }

    Socket::~Socket() {
        close();
        block();

        delete thread;
    }

    void Socket::block() {
        if (thread->joinable()) thread->join();
    }

    void Socket::close() {
        running = false;

#if defined(__linux__) || defined(__APPLE__)
        ::close(socketHandle);
#elif _WIN32
        closesocket(socketHandle);
#endif
    }

    void Socket::send(const exolix::net::SocketMessage &message) const {
        if (!running) return;

#if defined(__linux__) || defined(__APPLE__)
        write(socketHandle, message.data, message.size);
#elif _WIN32
        ::send(socketHandle, message.data, message.size, 0);
#endif
    }

    void Socket::send(const std::string &message) const {
        send(SocketMessage {
                const_cast<char *>(message.c_str()),
                message.size()
        });
    }

    void Socket::setOnMessageListener(const std::function<void(SocketMessage &)> &listener) {
        this->onMessage = listener;
    }

    // SocketServer
    SocketServer::SocketServer(const exolix::net::SocketServerOptions &options):
        options(options) {
    }

    SocketServer::~SocketServer() {
        isListening = false;
        block();

        delete thread;
    }

    void SocketServer::listen(uint16_t listeningPort, const std::string &listeningHost) {
        if (hasStartedBefore)
            throw SocketError(SocketErrors::ServerPreviouslyStartedBefore, "Cannot start the server again because it was started before.");

        hasStartedBefore = true;

        this->port = listeningPort;
        this->host = listeningHost;

#if defined(__linux__) || defined(__APPLE__)
        struct sockaddr_in serverAddress {};

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

        if (::listen(osSocketHandle, (int) options.backlog) < 0)
            throw SocketError(SocketErrors::ServerCannotListen, "The OS could not listen on the socket.");

        isListening = true;
        thread = new std::thread([this] () {
            while (isListening) {
                int clientSocketHandle;
                struct sockaddr_in clientAddress {};
                const int clientAddressLength = sizeof(clientAddress);

                if ((clientSocketHandle = accept(osSocketHandle, (struct sockaddr *) &clientAddress, (socklen_t *) &clientAddressLength)) >= 0) {
                    std::thread([this, &clientSocketHandle] () {
                        Socket socket(clientSocketHandle);
                        sockets.insert({ clientSocketHandle, socket });

                        onSocketOpen(socket);
                    }).detach();
                } else {
                    std::cout << "Error: " << errno << std::endl;
                }
            }
        });
#elif _WIN32
    WSADATA wsaData;
    int iResult;

    SOCKET listenSocket = INVALID_SOCKET;
    SOCKET clientSocket = INVALID_SOCKET;

    struct addrinfo *result = NULL;
    struct addrinfo hints;

    iResult = WSAStartup(CMAKEWORD(2, 2), &wsaData);
    if (iResult != 0)
        throw SocketError(SocketErrors::ServerCannotStartWSA, "The WSA could not be started.");

    ZeroMemory(&hints, sizeof(hints));

    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;
    hints.ai_flags = AI_PASSIVE;

    iResult = getaddrinfo(NULL, std::to_string(port).c_str(), &hints, &result);
    if (iResult != 0)
        throw SocketError(SocketErrors::ServerInvalidHost, "The host could not be resolved.");

    listenSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
    if (listenSocket == INVALID_SOCKET) {
        freeaddrinfo(result);
        WSACleanup();

        throw SocketError(SocketErrors::ServerCannotCreateSocket, "The socket could not be created for windows reason: " + std::to_string(WSAGetLastError()) + ".");
    }

    iResult = bind(listenSocket, result->ai_addr, (int) result->ai_addrlen);
    if (iResult == SOCKET_ERROR) {
        freeaddrinfo(result);
        closesocket(listenSocket);
        WSACleanup();

        throw SocketError(SocketErrors::ServerCannotBind, "The socket could not be bound for windows reason: " + std::to_string(WSAGetLastError()) + ".");
    }

    freeaddrinfo(result);
    iResult = listen(listenSocket, SOMAXCONN);

    if (iResult == SOCKET_ERROR) {
        closesocket(listenSocket);
        WSACleanup();

        throw SocketError(SocketErrors::ServerCannotListen, "The socket could not be listened for windows reason: " + std::to_string(WSAGetLastError()) + ".");
    }

    isListening = true;

    thread = new std::thread([this] () {
        while (isListening) {

        }
    });
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
