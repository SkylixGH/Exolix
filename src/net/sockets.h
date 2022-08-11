#pragma once

#include <vector>
#include "util/states.h"
#include <cstdint>
#include <string>

namespace exolix::net {
    /**
     * A socket instance from the SocketServer which
     * lets you interact with the client connection.
     */
    class Socket {

    };

    /**
     * Settings for the SocketServer.
     */
    struct SocketServerSettings {
        /**
         * The server's listening port.
         */
        uint16_t port = 0;

        /**
         * The maximum number of pending connections.
         */
        int backlog = 128;

        /**
         * The maximum number of open connections allowed.
         */
        int maxOpen = 500;

        /**
         * SSL certificate key.
         */
        std::string sslKey;

        /**
         * SSL certificate.
         */
        std::string sslCert;
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
        SocketServer(const uint16_t inPort);
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
