#pragma once

#include "address.h"

namespace exolix {
    /**
     * A TCP server implementation with cross-platform support
     * and TLS capabilities.
     */
    class SocketServer {
    private:

    public:
        /**
         * Create a new socket server.
         */
        SocketServer(NetAddress address, int backlog);

        ~SocketServer();
    };
}
