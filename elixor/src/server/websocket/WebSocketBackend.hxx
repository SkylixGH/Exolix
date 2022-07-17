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
                /**
                 * Create a new API gateway server.
                 *
                 * @param port The server port to listen on.
                 * @param host The host that the server will open on.
                 */
                WebSocketBackend();
                ~WebSocketBackend();
            };
        } // namespace WebSocket
    } // namespace Server
} // namespace Elixor
