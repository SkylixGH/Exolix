#pragma once

#include "address.h"
#include <functional>

namespace exolix {
    /**
     * A TCP server implementation with cross-platform support
     * and TLS capabilities.
     */
    class SocketServer {
    private:
        /**
         * The address of the server.
         */
        const NetAddress address;

        /**
         * The backlog of the server.
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
         * Whether the server is busy.
         */
        bool busy = false;

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
        void setTLS(bool enabled);

        /**
         * Set the TLS certificate of the server.
         * @param path The path to the certificate.
         */
        void setCertificate(const std::string &path);

        /**
         * Set the TLS private key of the server.
         * @param path The path to the private key.
         */
        void setPrivateKey(const std::string &path);

        /**
         * Load the server.
         */
        void load();
    };
}
