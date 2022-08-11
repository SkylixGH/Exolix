#pragma once

#include <vector>

namespace exolix::net {
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
        std::vector<Socket *> sockets;


    public:
    };

    /**
     * A client connector for connecting to TCP servers
     * with a high level cross-platform API.
     */
    class SocketClient {

    };
}
