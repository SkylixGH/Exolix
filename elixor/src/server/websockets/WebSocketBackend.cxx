#include "WebSocketBackend.hxx"
#include "../../internal/ElixorError.hxx"
#include "./errors/ServerErrors.hxx"
#include <iostream>

using Elixor::Internal::ElixorError;
using Elixor::Server::WebSockets::Errors::ServerErrors;

namespace Elixor {
    namespace Server {
        namespace WebSockets {
            WebSocketServer::WebSocketServer(const int port,
                                             const std::string host) {
                this->port = port;
                this->host = host;
            }

            WebSocketServer::~WebSocketServer() { this->stop(); }

            void WebSocketServer::start() {
                this->online = true;
                this->errorIfRunning();
            }

            void WebSocketServer::stop() {}

            void WebSocketServer::errorIfRunning() {
                if (!this->online)
                    return;
                throw ElixorError(
                    Errors::BINDING_RESOURCE_CHANGED_WHILE_ONLINE,
                    "Could not apply the new server settings, the server is "
                    "already online, please stop it before changing settings "
                    "relating to the server binding stages");
            }

            void WebSocketServer::validateSettings() {}
        } // namespace WebSockets
    }     // namespace Server
} // namespace Elixor
