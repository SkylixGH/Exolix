#pragma once

#include <exolix/net.h>
#include <exolix/err.h>
#include <functional>

namespace exolix::http {
    class HttpHeaders {

    };

    class HttpRequest {

    };

    class HttpResponse {

    };

    class HttpServer : public net::SocketServer {
    private:
        std::function<void(HttpRequest &, HttpResponse &)> onHttpRequest = [] (HttpRequest &request, HttpResponse &response) {};

    public:

    };
}
