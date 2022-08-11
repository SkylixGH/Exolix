#include "sockets.h"

#include <utility>

namespace exolix::net {
    SocketServer::SocketServer(const uint16_t inPort): port(inPort) {}

    SocketServer::~SocketServer() {
        unbind();
    }

    void SocketServer::bind() {

    }

    void SocketServer::unbind() {

    }
}
