#include "sockets.h"
#include <utility>
#include <string.h>

#ifdef __linux__

#include <netinet/in.h>
#include <cerrno>

#elif _WIN32

#include <winsock2.h>

#endif

namespace exolix::net {
    SocketServer::SocketServer(uint16_t inPort) : port(inPort) {}

    SocketServer::~SocketServer() {
        unbind();
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

        sysServerID = socket(AF_INET, SOCK_STREAM, 0);

        if (sysServerID < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_CREATE_SOCKET,
                    "The operating system could not create the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        if (setsockopt(sysServerID, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &option, sizeof(option))) {
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

        if (::bind(sysServerID, (struct sockaddr *) &address, addressLength) < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_SOCKET_BIND,
                    "The operating system could not bind the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        if (listen(sysServerID, backlog) < 0) {
            state = util::JobState::OFF;

            throw SocketError(
                    SocketErrors::SERVER_FAILED_SOCKET_LISTEN,
                    "The operating system could not listen on the socket "
                    "because of the following error received by the OS, "
                    "'" + getLastSocketErrorMessage() + "'"
            );
        }

        state = util::JobState::READY;
        while (state == util::JobState::READY) {

        }
#endif
    }

    void SocketServer::unbind() {

    }
}
