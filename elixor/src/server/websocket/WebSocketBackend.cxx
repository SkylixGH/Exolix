#include "WebSocketBackend.hxx"

namespace Elixor {
    namespace Server {
        namespace WebSocket {
            WebSocketBackend::WebSocketBackend(const int port, const std::string host) {
                this->port = port;
                this->host = host;
            }
        } // namespace WebSocket
    } // namespace Server
} // namespace Elixor
