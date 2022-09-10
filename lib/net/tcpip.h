#pragma once

#include "address.h"
#include "../thread/thread.h"
#include <functional>

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
         * The thread was already blocked and cannot be blocked
         * again.
         */
        ServerAlreadyThreadBlocked,

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
         ServerDangerousActionWhileOnline
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
        Thread *serverThread{};

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
        bool isOnline();

        /**
         * This function will return a boolean value indicating whether the
         * server is busy or not.
         */
        bool isBusy();
    };
}
