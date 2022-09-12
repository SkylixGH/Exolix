#pragma once

#include "../number/types.h"
#include "address.h"
#include "../thread/thread.h"
#include <functional>
#include <optional>
#include <openssl/ssl.h>

namespace exolix {
    /**
     * This is an enumerated list of possible error codes
     * that can be returned by members of the socket server.
     */
    enum class SocketServerErrors {
        /**
         * No errors occurred. The action has completed
         * successfully.
         */
        Ok,

        /**
         * The server performed an action that required
         * the server to be actively online and not busy,
         * but the action was still requested to be performed.
         */
        ServerNotReadyForAction,

        /**
         * The server had an operation requested to be performed
         * on it, but the server was unsafe to operate on with this
         * specific job. The server must be inactive and not busy for
         * the action to be performed.
         */
        ServerDangerousActionWhileOnline,

        /**
         * The server was requested to be blocked but the server
         * has already previously been blocked, without experiencing
         * a restart. Please restart the server to block the server
         * process thread again.
         */
        CannotBlockServerAfterPreviouslyBlockedWithoutRestart,

        /**
         * The server was requested to work and set a TLS certificate
         * or key but the server did not have TLS mode enabled.
         */
         TlsNotEnabled,

         /**
          * The server address provided is faulty and cannot be used
          * to create a server.
          */
         FaultyAddress,

         /**
          * Could not create server socket instance for the server for an
          * unknown reason.
          */
         CouldNotCreateServerSocketInstance,

         /**
          * A permission error occurred when creating the socket.
          */
         PermissionFaulty,

         /**
          * IPv4 or IPv6 is not supported on the system.
          */
         IpVersionNotSupported,

         /**
          * Too many descriptors are open on the system.
          */
         TooManyDescriptorsOpen,

         /**
          * The server does not have enough memory to start.
          */
         FaultyMemoryAccess,

         /**
          * The server could not set the socket option.
          */
         CouldNotSetSocketOption,
    };

    /**
     * This is a class instance of a socket server client connection.
     */
    class SocketServerAdapter {
    private:
        /**
         * The SSL client context. This is used for
         * TLS connections.
         */
        std::optional<SSL *> cSsl;

        /**
         * The socket file descriptor. This is used
         * for all socket connections.
         */
        i64 cSocket;

        /**
         * The client listening thread. This is used
         * for listening to the client socket.
         */
        Thread *cThread {};

        /**
         * Whether the socket is currently connected
         * to a client.
         */
        bool cConnected = true;

    public:
        /**
         * Create a new socket instance.
         */
        SocketServerAdapter(i64 socketFd, std::optional<SSL *> ssl = std::nullopt);
    };

    /**
     * A TCP server implementation with cross-platform support
     * and TLS capabilities.
     */
    class SocketServer {
    private:
        /**
         * The address of the server. This is used for
         * binding the server to a specific address.
         */
        const NetAddress address;

        /**
         * The backlog of the server. A backlog number
         * is the maximum number of connections that can
         * be queued.
         */
        const int backlog;

        /**
         * Whether the server is online.
         */
        bool online = false;

        /**
         * Whether the server is using TLS.
         */
        bool tls = false;

        /**
         * Path to the TLS certificate.
         */
        std::string certificate;

        /**
         * Path to the TLS private key.
         */
        std::string privateKey;

        /**
         * Whether the server is busy. When busy the server
         * should not allow another load or unload action
         * while busy.
         */
        bool busy = false;

        /**
         * The server thread where all of the base processing will
         * be performed such as connection accepting and dispatching.
         */
        Thread *serverThread {};

        /**
         * Receive buffer size, this is the maximum amount of data
         * in bytes that the server can read in a single message.
         */
        int receiveBufferSize = 1024;

        /**
         * The TLS context. This is used for TLS connections.
         * This is only used when TLS is enabled.
         */
        std::optional<SSL_CTX *> sslContext = std::nullopt;

        /**
         * The socket file descriptor. This is used for all
         * socket connections.
         */
        i64 socketFd = -1;

        /**
         * Clean up the server variables after a crash
         * and reset all properties to defaults.
         */
        void cleanUp();

    public:
        /**
         * Create a new socket server.
         * @param address The address to bind to.
         * @param backlog The maximum amount of connections to queue.
         */
        SocketServer(NetAddress address, int backlog);

        ~SocketServer();

        /**
         * Set the TLS mode of the server.
         * @param enabled Whether to enable TLS.
         */
        SocketServerErrors setTLS(bool enabled);

        /**
         * Set the TLS certificate of the server.
         * @param path The path to the certificate.
         */
        SocketServerErrors setCertificate(const std::string &path);

        /**
         * Set the TLS private key of the server.
         * @param path The path to the private key.
         */
        SocketServerErrors setPrivateKey(const std::string &path);

        /**
         * Load the server. When loading, the server cannot perform any
         * other load or unload actions. It will be marked busy until
         * the function succeeds or fails.
         */
        SocketServerErrors load();

        /**
         * Unload the server. When unloading, the server cannot perform
         * any other load or unload actions. It will be marked busy until
         * the function succeeds or fails.
         */
        SocketServerErrors unload();

        /**
         * This will block the outer thread of where this function is called.
         * When called, the thread will be blocked until the server is unloaded.
         */
        SocketServerErrors block();

        /**
         * This function will return a boolean value indicating whether the
         * server is online or not.
         */
        [[nodiscard]] bool isOnline() const;

        /**
         * This function will return a boolean value indicating whether the
         * server is busy or not.
         */
        [[nodiscard]] bool isBusy() const;

        /**
         * Set the receive buffer size of the server.
         * @param size The size of the receive buffer.
         */
        SocketServerErrors setReceiveBufferSize(u16 size);
    };
}
