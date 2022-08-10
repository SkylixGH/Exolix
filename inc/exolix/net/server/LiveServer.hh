#pragma once

#include "Protocol.hh"
#include "low/SocketServer.hh"
#include <thread>

namespace exolix::net::server {
    class LiveServer {
    private:
        low::SocketServer *server = nullptr;
        std::thread *thread = nullptr;
        bool online = false;

    public:
        LiveServer(Protocol protocol, uint16_t port, uint16_t backlog);
        ~LiveServer();

        void live();
        void block();
        void kill();
    };
}
