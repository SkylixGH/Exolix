#include <exolix.h>

namespace exolix::net::server {
    GatewayServer::GatewayServer(Protocol protocol, uint16_t port, uint16_t backlog) {

    }

    GatewayServer::~GatewayServer() {
        kill();

        delete thread;
        delete server;
    }

    void GatewayServer::live() {
        if (online) return;
        online = true;

        thread = new std::thread([this] () {

        });
    }

    void GatewayServer::block() {
        if (thread != nullptr && thread->joinable()) thread->join();
    }

    void GatewayServer::kill() {
        if (!online) return;
        online = false;

        thread = nullptr;
    }
}
