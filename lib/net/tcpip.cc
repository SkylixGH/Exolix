#include "tcpip.h"

#include <utility>

namespace exolix {
    SocketServer::SocketServer(exolix::NetAddress address, int backlog) :
            address(std::move(address)), backlog(backlog) {
    }

    SocketServer::~SocketServer() {

    }

    SocketServerErrors SocketServer::setTLS(bool enabled) {
        tls = enabled;
    }

    SocketServerErrors SocketServer::setCertificate(const std::string &path) {
        certificate = path;
    }

    SocketServerErrors SocketServer::setPrivateKey(const std::string &path) {
        privateKey = path;
    }

    SocketServerErrors SocketServer::load() {

    }

    SocketServerErrors SocketServer::unload() {

    }

    bool SocketServer::isOnline() {

    }

    bool SocketServer::isBusy() {

    }

    SocketServerErrors SocketServer::block() {

    }
}
