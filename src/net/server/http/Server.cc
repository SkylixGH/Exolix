#include <exolix.h>

#include <utility>

namespace exolix::net::server::http {
    Server::Server(uint16_t port, uint16_t backlog) {
        this->backlog = backlog;
        this->port = port;
    }

    Server::~Server() {
        kill();

        delete server;
        delete thread;
    }

    void Server::setOnRequest(std::function<void()> onRequestFn) {
        this->onRequest = std::move(onRequestFn);
    }

    void Server::spawn() {
        if (online) return;
        online = true;

        thread = new std::thread([this] () {
            this->server = new system::SocketServer(this->port, this->backlog);
        });
    }

    void Server::block() {
        if (thread != nullptr && thread->joinable()) thread->join();
    }

    void Server::kill() {
        if (!online) return;

        online = false;
        thread = nullptr;
        server = nullptr;
    }
}
