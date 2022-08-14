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
         /**
          * The server is already online.
          */
         SERVER_ALREADY_ONLINE,

         /**
          * The server is already working to bind.
          */
         SERVER_ALREADY_ENABLING,

         /**
          * The server is already offline.
          */
         SERVER_ALREADY_OFFLINE,

         /**
          * The server is already disabling.
          */
         SERVER_ALREADY_DISABLING,

         /**
          * The server could not create the socket.
          */
         SERVER_FAILED_CREATE_SOCKET,

         /**
          * The server could not assign the options.
          */
         SERVER_FAILED_SOCKET_OPTIONS,

         /**
          * The server could not bind the server.
          */
         SERVER_FAILED_SOCKET_BIND,

         /**
          * The server failed to listen.
          */
         SERVER_FAILED_SOCKET_LISTEN,

         /**
          * The server port was being changed while running,
          * it must be unbound before changing the port.
          */
         SERVER_PORT_CANNOT_CHANGE_WHILE_ONLINE,

         /**
          * The server address provided to be connected to is invalid.
          */
         CLIENT_INVALID_HOST
     };

     class SocketMessage {
     public:
         explicit SocketMessage(char *data);

         char *raw;
         size_t size;
         [[nodiscard]] std::string toString() const;
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

        std::function<void (SocketMessage *)> onMessage {};

    public:
        const int socketHandle;

        explicit Socket(int osSocketID);
        ~Socket();

        void close();
        void send(const std::string& message) const;
        void send(const char* message) const;
        void block();

        void setOnMessage(std::function <void (SocketMessage *)> onMessageFn);

        [[nodiscard]] bool isLive() const;
    };

    /**
     * A server for hosting TCP servers with a high
     * level cross-platform API.
     */
    class SocketServer {
    private:
        util::JobState state;
        uint16_t port;
        int sysServerId {};
        int backlog = 128;
        int maxConnections = 512;
        std::function<void(int)> onSocketOpen {};
        std::thread *serverThread = nullptr;

        static std::string getLastSocketErrorMessage();

    public:
        explicit SocketServer(uint16_t inPort);
        ~SocketServer();

        /**
         * Start the socket server and listen on the port.
         */
        void bind();

        void block();

        /**
         * Unbind the socket server.
         */
        void unbind();

        /**
         * Set the socket connection listener.
         * @param onSocketOpenFn The listener function.
         */
        void setOnSocketOpen(std::function<void(int)> onSocketOpenFn);
    };

    /**
     * A client connector for connecting to TCP servers
     * with a high level cross-platform API.
     */
    class SocketClient {

    };
}
