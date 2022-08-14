#include "sockets.h"
#include <utility>
#include <cstring>
#include <thread>
#include <future>

#ifdef __linux__

#include <netinet/in.h>
#include <cerrno>
#include <unistd.h>

#elif _WIN32

#include <winsock2.h>

#elif __APPLE__

#include <cerrno>

#endif

namespace exolix::net {
    SocketMessage::SocketMessage(char *dataIn): raw(dataIn) {
        size = strlen(dataIn);
    }

    std::string SocketMessage::toString() const {
        return std::string { raw, size };
    }

    Socket::Socket(int osSocketID): socketHandle(osSocketID) {
        listener = std::thread([this] () {
            char buffer[1024] = { 0 };

            while (live) {
                const int closeCode = 0;
                const int fd = socketHandle;
                long readResult = read(fd, buffer, sizeof(buffer));

                if (readResult == closeCode) {
                    break;
                } else {
                    if (onMessage)
                        std::thread([&] () { onMessage(new SocketMessage(buffer)); }).join();
                }
            }
        });
    }

    Socket::~Socket() {
        live = false;

        if (listener.joinable())
            listener.join();

        close();
    }

    void Socket::block() {
        if (listener.joinable())
            listener.join();
    }

    void Socket::setOnMessage(std::function<void(SocketMessage *)> onMessageFn) {
        onMessage = std::move(onMessageFn);
    }

    void Socket::close() {
        live = false;
        ::close(socketHandle);
    }

    void Socket::send(const std::string& message) const {
        if (!live) return;
        write(socketHandle, message.c_str(), message.length());
    }

    void Socket::send(const char* message) const {
        if (!live) return;
        write(socketHandle, message, strlen(message));
    }

    bool Socket::isLive() const {
        return live;
    }

    SocketServer::SocketServer(uint16_t inPort) : port(inPort) {}

    SocketServer::~SocketServer() {
        if (state == util::JobState::READY)
            unbind();

        delete serverThread;
    }

    std::string SocketServer::getLastSocketErrorMessage() {
#ifdef __linux__
        return strerror(errno);
#elif _WIN32
        static char message[256] = {0};

        FormatMessage(
            FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
            0, WSAGetLastError(), 0, message, 256, 0);

        char *nl = strrchr(message, '\n');
        if (nl) *nl = 0;
        return message;
#elif __APPLE__
        return strerror(errno);
#endif
    }

    void SocketServer::bind() {
        if (state == util::JobState::READY)
            throw SocketError(
                    SocketErrors::SERVER_ALREADY_ONLINE,
                    "The socket server is already bound to the address, "
                    "please unbind it to re-bind it to the address"
            );
        else if (state == util::JobState::ENABLING)
            throw SocketError(
                    SocketErrors::SERVER_ALREADY_ENABLING,
                    "The socket server is already trying to bind "
                    "to the address, you cannot rebind it unless its "
                    "offline first"
            );

        state = util::JobState::ENABLING;

#ifdef __linux__
        struct sockaddr_in address{};

        const int addressLength = sizeof(address);
        const int option = 1;

        sysServerId = socket(AF_INET, SOCK_STREAM, 0);

        if (sysServerId < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_CREATE_SOCKET,
                    "The operating system could not create the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        if (setsockopt(sysServerId, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &option, sizeof(option))) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_SOCKET_OPTIONS,
                    "The operating system could not set the socket options "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        address.sin_family = AF_INET;
        address.sin_addr.s_addr = INADDR_ANY;
        address.sin_port = htons(port);

        if (::bind(sysServerId, (struct sockaddr *) &address, addressLength) < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_SOCKET_BIND,
                    "The operating system could not bind the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        if (listen(sysServerId, backlog) < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_SOCKET_LISTEN,
                    "The operating system could not listen on the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        state = util::JobState::READY;

        serverThread = new std::thread([this, &addressLength, &address] () {
            while (state == util::JobState::READY) {
                const int clientSocketHandle = accept(sysServerId, (struct sockaddr *) &address,
                                                      (socklen_t *) &addressLength);

                if (clientSocketHandle >= 0) {
                    if (onSocketOpen)
                        std::thread([&]() { onSocketOpen(clientSocketHandle); }).detach();
                }
            }
        });
#endif
    }

    void SocketServer::unbind() {
        if (state == util::JobState::ENABLING) {
            throw SocketError(
                    SocketErrors::SERVER_BUSY_ENABLING,
                    "Cannot unbind the socket server while it's enabling"
            );
        } else if (state == util::JobState::OFF) {
            throw SocketError(
                SocketErrors::SERVER_ALREADY_OFFLINE,
                "Cannot unbind the socket server because its already offline"
            );
        } else if (state == util::JobState::DISABLING) {
            throw SocketError(
                    SocketErrors::SERVER_ALREADY_DISABLING,
                    "Cannot try to unbind the socket server again because "
                    "it is already unbinding"
            );
        }

        state = util::JobState::DISABLING;
        sysServerId = 0;

        if (serverThread) {
            serverThread->join();
            serverThread = nullptr;

            delete serverThread;
        }
    }

    void SocketServer::setOnSocketOpen(std::function<void(int)> onSocketOpenFn) {
        onSocketOpen = std::move(onSocketOpenFn);
    }

    void SocketServer::block() {
        if (serverThread && serverThread->joinable())
            serverThread->join();
    }
}
