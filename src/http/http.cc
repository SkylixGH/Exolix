#include "http.h"

namespace exolix::http {
    HttpSocketManager::HttpSocketManager(exolix::http::HttpProtocolVersion protocolVersion):
        version(protocolVersion) {

    }

    void HttpSocketManager::handleConnect(exolix::net::Socket &socket) {

    }
}
