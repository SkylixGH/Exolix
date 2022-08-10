#include <exolix.h>

namespace exolix::net::server {
    LiveServer::LiveServer(Protocol protocol, uint16_t port, uint16_t backlog) {

    }

    LiveServer::~LiveServer() {
        kill();

        delete thread;
        delete server;
    }

    void LiveServer::live() {
        if (online) return;
        online = true;

        thread = new std::thread([this] () {

        });
    }

    void LiveServer::block() {
        if (thread != nullptr && thread->joinable()) thread->join();
    }

    void LiveServer::kill() {
        if (!online) return;
        online = false;

        thread = nullptr;
    }
}
