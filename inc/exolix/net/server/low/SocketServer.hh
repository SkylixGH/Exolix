#pragma once

#include <string>
#include <functional>

namespace exolix::net::server::low {
    class SocketServer {
    private:
        uint16_t port;
        uint16_t backlog;
        bool online = false;
        std::vector<int> sockets;
        std::function<void(int)> onAccept;
        std::function<void(int, std::string)> onMessage;
        std::function<void(int)> onClose;
        int serverHandle {};

    public:
        SocketServer(uint16_t port, uint16_t backlog);

        void setOnAccept(std::function<void(int)> onAccept);
        void setOnMessage(std::function<void(int, std::string)> onMessage);
        void setOnClose(std::function<void(int)> onClose);

        void bind();
        void unbind();

        unsigned long count();
    };
}
