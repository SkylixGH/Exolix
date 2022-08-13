#pragma once

#include "sockets.h"

namespace exolix::net {
    class HttpClient {

    };

    class HttpHeaders {

    };

    class HttpResponse {

    };

    class HttpInputRequest {

    };

    class HttpServer {
    private:
        SocketServer *server = nullptr;

    public:
        explicit HttpServer(int port = 80);
        ~HttpServer();

        void bind();
        void block();
    };
}
