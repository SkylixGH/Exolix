#include "http.h"

namespace exolix::net {
    HttpServer::HttpServer(int port) {
        server = new SocketServer(port);
    }

    HttpServer::~HttpServer() {
        delete server;
    }

    void HttpServer::bind() {
        server->bind();
    }

    void HttpServer::block() {
        server->block();
    }
}
