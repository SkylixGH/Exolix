#pragma once

#include <ixwebsocket/IXWebSocketServer.h>
#include <iostream>

namespace Elixor {
    namespace Server {
        namespace WebSocket {
            class WebSocketBackend {
            private:
                ix::WebSocketServer server = NULL;
                int port = NULL;
                std::string host;
                const bool sudoRights = geteuid() == 0;

            public:
                /**
                 * Create a new WebSocket API server.
                 *
                 * @param port The server port to listen on.
                 * @param host The host that the server will open on.
                 */
                WebSocketBackend();
                ~WebSocketBackend();

                /**
                 * Start the WebSocket API server.
                 */
                void start();

                /**
                 * Stop the WebSocket API server.
                 */
                void stop();
            };
        } // namespace WebSocket
    } // namespace Server
} // namespace Elixor
