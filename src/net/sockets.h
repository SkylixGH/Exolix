#pragma once

#include <vector>
#include "util/states.h"
#include <exolix/err.h>
#include <cstdint>
#include <string>
#include <thread>
#include <functional>

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
         SERVER_BUSY_ENABLING,
         SERVER_FAILED_CREATE_SOCKET,
         SERVER_FAILED_SOCKET_OPTIONS,
         SERVER_FAILED_SOCKET_BIND,
         SERVER_FAILED_SOCKET_LISTEN,
         SERVER_PORT_CANNOT_CHANGE_WHILE_ONLINE,
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
    private:
        bool live = true;
        std::thread listener;

        std::function<void (const std::string)> onMessage {};

    public:
        const int socketHandle;

        explicit Socket(int osSocketID);
        ~Socket();

        void close();
        void send(const std::string& message) const;
        void block();

        void setOnMessage(std::function <void (std::string)> onMessageFn);

        [[nodiscard]] bool isLive() const;
    };

    /**
     * A server for hosting TCP servers with a high
     * level cross-platform API.
     */
    class SocketServer {
    private:
        util::JobState state = util::JobState::OFF;
        uint16_t port;
        std::vector<Socket *> sockets {};
        int sysServerId {};
        int backlog = 128;
        int maxConnections = 512;
        std::function<void(Socket *)> onSocketOpen {};

        static std::string getLastSocketErrorMessage();

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

        /**
         * Set the socket connection listener.
         * @param onSocketOpenFn The listener function.
         */
        void setOnSocketOpen(std::function<void(Socket *)> onSocketOpenFn);
    };

    /**
     * A client connector for connecting to TCP servers
     * with a high level cross-platform API.
     */
    class SocketClient {

    };
}
