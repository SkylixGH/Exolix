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

            WebSocketServer::~WebSocketServer() {
                this->stop();

                delete this->server;
                delete this->thread;
            }

            void WebSocketServer::start() {
                this->online = true;

                this->thread = new std::thread([this]() {
                    this->server = new ix::WebSocketServer(this->port, this->host);

                    this->server->listen();
                    this->server->disablePerMessageDeflate();

                    this->server->start();
                    this->server->wait();
                });
            }

            void WebSocketServer::stop() {
                if (!this->online)
                    return;

                this->server->stop();
                this->online = false;
            }

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

            void WebSocketServer::block() {
                if (!this->online) return;
                if (this->thread == nullptr) return;
                if (this->thread->joinable()) this->thread->join();
            }
        } // namespace WebSockets
    }     // namespace Server
} // namespace Elixor
