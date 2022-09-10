#include "tcpip.h"

namespace exolix {
    SocketServer::SocketServer(exolix::NetAddress address, int backlog):
        address(address), backlog(backlog) {
    }

    SocketServer::~SocketServer() {

    }

    void SocketServer::setTLS(bool enabled) {
        tls = enabled;
    }

    void SocketServer::setCertificate(const std::string &path) {
        certificate = path;
    }

    void SocketServer::setPrivateKey(const std::string &path) {
        privateKey = path;
    }

    void SocketServer::load() {

    }
}
