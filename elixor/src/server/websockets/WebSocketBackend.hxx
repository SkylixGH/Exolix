#pragma once

#include <iostream>
#include <ixwebsocket/IXWebSocketServer.h>

namespace Elixor {
    namespace Server {
        namespace WebSockets {
            /**
             * A WebSocket backend API server.
             */
            class WebSocketServer {
            public:
                /**
                 * Create a new WebSocket API server.
                 *
                 * @param port The server port to listen on, use 0 to not bind
                 * to a port, and -1 to bind to any port.
                 * @param host The host that the server will open on.
                 */
                WebSocketServer(const int port = 0,
                                const std::string host = "0.0.0.0");
                ~WebSocketServer();

                /**
                 * Start the WebSocket API server.
                 */
                void start();

                /**
                 * Stop the WebSocket API server.
                 */
                void stop();

                /**
                 * This blocks the current thread until the server is stopped;
                 */
                void block();

            private:
                /**
                 * The core WebSocket server.
                 */
                ix::WebSocketServer * server;

                /**
                 * The thread for the server to run inside of.
                 */
                std::thread * thread;

                /**
                 * The chosen hostname to bind to.
                 */
                std::string host;

                /**
                 * Whether the server is online and bound to an address.
                 */
                bool online = false;

                /**
                 * The chosen port to bind to.
                 */
                int port = 0;

                /**
                 * Throw an error if the server is running.
                 */
                void errorIfRunning();

                /**
                 * Validate settings, otherwise throw an error.
                 */
                void validateSettings();
            };
        } // namespace WebSockets
    }     // namespace Server
} // namespace Elixor
