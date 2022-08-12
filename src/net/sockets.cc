#include "sockets.h"

#include <utility>

namespace exolix::net {
    SocketServer::SocketServer(uint16_t inPort) : port(inPort) {}

    SocketServer::~SocketServer() {
        unbind();
    }

    void SocketServer::bind() {
        if (state == util::JobState::READY)
            throw SocketError(
                    SocketErrors::SERVER_ALREADY_ONLINE,
                    "The socket server is already bound to the address, "
                    "please unbind it to re-bind it to the address"
            );
        else if (state == util::JobState::ENABLING)
            throw SocketError(
                    SocketErrors::SERVER_ALREADY_ENABLING,
                    "The socket server is already trying to bind "
                    "to the address, you cannot rebind it unless its "
                    "offline first"
            );

        state = util::JobState::ENABLING;
    }

    void SocketServer::unbind() {

    }
}
