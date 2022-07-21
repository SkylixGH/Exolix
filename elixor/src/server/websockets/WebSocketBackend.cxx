#include "WebSocketBackend.hxx"

namespace Elixor {
    namespace Server {
        namespace WebSockets {
            WebSocketServer::WebSocketServer(const int port,
                                             const std::string host) {
                this->port = port;
                this->host = host;
            }

            WebSocketServer::~WebSocketServer() { this->stop(); }

            void WebSocketServer::start() {}

            void WebSocketServer::stop() {}

            void WebSocketServer::errorIfRunning() {}

            void WebSocketServer::validateSettings() {}
        } // namespace WebSockets
    }     // namespace Server
} // namespace Elixor
