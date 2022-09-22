#pragma once

#include "impl.hxx"

namespace exolix {
    /**
     * An implementation wrapper for the Win32 Winsock2 server
     * listener based on the TCP protocol.
     */
    class SocketServerWin32 : SocketServerImpl {
    private:
        /**
         * Listen on the address configured.
         */
        void listen() override;
    };
}
