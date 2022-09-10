#include "tcpip.h"

#include <utility>

namespace exolix {
    SocketServer::SocketServer(exolix::NetAddress address, int backlog) :
            address(std::move(address)), backlog(backlog) {
    }

    SocketServer::~SocketServer() {
        if (serverThread != nullptr)
            serverThread->block();

        delete serverThread;
    }

    SocketServerErrors SocketServer::setTLS(bool enabled) {
        tls = enabled;
        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    SocketServerErrors SocketServer::setCertificate(const std::string &path) {
        certificate = path;
        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    SocketServerErrors SocketServer::setPrivateKey(const std::string &path) {
        privateKey = path;
        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    SocketServerErrors SocketServer::load() {
        if (online)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        busy = true;
        online = true;

        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    SocketServerErrors SocketServer::unload() {
        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    bool SocketServer::isOnline() const {
        return online;
    }

    bool SocketServer::isBusy() const {
        return busy;
    }

    SocketServerErrors SocketServer::block() {
        if (serverThread == nullptr)
            return SocketServerErrors::ServerNotReadyForAction;

        return SocketServerErrors::Ok; // TODO: Implement correctly
    }
}
