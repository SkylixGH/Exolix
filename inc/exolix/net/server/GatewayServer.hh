#pragma once

#include "Protocol.hh"
#include "low/SocketServer.hh"
#include <thread>

namespace exolix::net::server {
    class GatewayServer {
    private:
        low::SocketServer *server = nullptr;
        std::thread *thread = nullptr;
        bool online = false;

    public:
        GatewayServer(Protocol protocol, uint16_t port, uint16_t backlog);
        ~GatewayServer();

        void live();
        void block();
        void kill();
    };
}
