#pragma once

#include <exolix/net/server/system/SocketServer.hh>
#include <thread>
#include <functional>

namespace exolix::net::server::http {
    class Server {
    private:
        system::SocketServer *server = nullptr;
        uint16_t backlog;
        bool online = false;
        uint16_t port;
        std::thread *thread = nullptr;
        std::function<void()> onRequest {};

    public:
        Server(uint16_t port, uint16_t backlog);
        ~Server();

        void setOnRequest(std::function<void()> onRequestFn);

        void spawn();
        void block();
        void kill();
    };
}