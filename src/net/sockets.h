#pragma once

#include <vector>
#include "util/states.h"
#include <exolix/err.h>
#include <cstdint>
#include <string>

namespace exolix::net {
    /**
     * An enumerated list containing all error
     * codes for the Exolix networking socket system.
     */
     enum class SocketErrors {
         SERVER_ALREADY_ONLINE,
         SERVER_ALREADY_ENABLING,
         SERVER_ALREADY_OFFLINE,
         SERVER_ALREADY_DISABLING,
         SERVER_OPEN_PERMISSION_DENIED,
         CLIENT_INVALID_HOST
     };

    /**
     * An error system for the Exolix networking library.
     */
    typedef err::Error<SocketErrors> SocketError;

    /**
     * A socket instance from the SocketServer which
     * lets you interact with the client connection.
     */
    class Socket {

    };

    /**
     * A server for hosting TCP servers with a high
     * level cross-platform API.
     */
    class SocketServer {
    private:
        util::JobState state = util::JobState::OFF;
        uint16_t port;
        const std::vector<Socket *> sockets {};

    public:
        explicit SocketServer(uint16_t inPort);
        ~SocketServer();

        /**
         * Start the socket server and listen on the port.
         */
        void bind();

        /**
         * Unbind the socket server.
         */
        void unbind();
    };

    /**
     * A client connector for connecting to TCP servers
     * with a high level cross-platform API.
     */
    class SocketClient {

    };
}
