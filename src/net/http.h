#pragma once

#include "sockets.h"
#include <vector>
#include <map>
#include <string>

namespace exolix::net {
    enum class HttpVersion {
        HTTP_1_0,
        HTTP_1_1
    };

    enum class HttpMethod {
        POST,
        GET,
        PUT,
        DELETE,
        NONE
    };

    class HttpHeaders {
    private:
        std::vector<std::string> headersKeys {};
        std::map<std::string, std::string> headersUnordered {};
        std::string path;
        std::string initialLine;
        HttpMethod method = HttpMethod::NONE;
        HttpVersion httpVersion = HttpVersion::HTTP_1_0;

        void applyInitialLine();

    public:
        HttpHeaders();
        explicit HttpHeaders(const std::string &headersStrings);

        void set(const std::string& key, const std::string& value);
        void remove(const std::string& key);

        std::string get(const std::string& key);
        std::string toString();

        std::string getVersionString();
        std::string getMethodString();

        HttpMethod getMethod();
        HttpVersion getVersion();
        std::string getPath();

        void setMethod(HttpMethod inMethod);

        void setVersion(HttpVersion inVersion);
        void setPath(const std::string& inPath);

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
        SocketServer *server;

    public:
        explicit HttpServer(int port = 80);
        ~HttpServer();

        void bind();
        void block();
    };
}
