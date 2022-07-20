#pragma once

#include <ixwebsocket/IXWebSocketServer.h>
#include <iostream>

namespace Elixor {
    namespace Server {
        namespace WebSocket {
            class WebSocketBackend {
            private:
                /**
                 * The actual websocket server.
                 */
                ix::WebSocketServer server = NULL;
                std::string host;
                bool online = false;
                int port = NULL;

            public:
                /**
                 * Create a new WebSocket API server.
                 *
                 * @param port The server port to listen on.
                 * @param host The host that the server will open on.
                 */
                WebSocketBackend(const int port = NULL, const std::string host = "localhost");
                ~WebSocketBackend();

                /**
                 * Start the WebSocket API server.
                 */
                void start();

                /**
                 * Stop the WebSocket API server.
                 */
                void stop();

            private:
                /**
                 * Throw an error if the server is running.
                 */
                 void errorIfRunning();
            };
        } // namespace WebSocket
    } // namespace Server
} // namespace Elixor
