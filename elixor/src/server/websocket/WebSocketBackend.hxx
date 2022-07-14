#pragma once

#include <ixwebsocket/IXWebSocketServer.h>

namespace Elixor {
namespace Server {
namespace WebSocket {
    class WebSocketBackend {
    private:
        bool hasInitiallyStarted = false;
        ix::WebSocketServer server;

    public:
        WebSocketBackend();
        ~WebSocketBackend();
    };
}
}
}