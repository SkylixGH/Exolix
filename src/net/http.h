#pragma once

#include "sockets.h"
#include <vector>
#include <map>
#include <string>

namespace exolix::net {
    class HttpHeaders {
    private:
        std::vector<std::string> headersKeys {};
        std::map<std::string, std::string> headersUnordered {};
        std::string httpInitialLine = "HTTP/1.1 200 OK";

    public:
        HttpHeaders();
        explicit HttpHeaders(const std::string &headersStrings);

        void set(const std::string& key, const std::string& value);
        void remove(const std::string& key);
        void writeInitialLine(const std::string &line);

        std::string get(const std::string& key);
        std::string toString();
        std::string getInitialLine();
    };

    class HttpResponse {
    private:
        HttpHeaders headers;
        std::string body;
    };

    class HttpInputRequest {
    private:
        HttpHeaders headers;
        std::string body;
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
