#pragma once

#include "../address.hxx"
#include <functional>

namespace exolix {
    class Socket {

    };

    class SocketServer {
    private:
        std::function<void(Socket &socket)> onAccept = [] (Socket &socket) {};

        void accept();

    public:
        explicit SocketServer(NetAddress &address);
        ~SocketServer();

        void listen();
        void stop();
        void block();

        void setOnAcceptListener(std::function<void(Socket &socket)> listener);
    };
}
