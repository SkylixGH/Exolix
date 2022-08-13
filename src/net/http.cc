#include <sstream>
#include "http.h"
#include <exolix/str.h>
#include <vector>
#include <string>

namespace exolix::net {
    HttpHeaders::HttpHeaders() = default;

    HttpHeaders::HttpHeaders(const std::string &headersStrings) {
        std::vector<std::string> headers = exolix::str::StringModify::split(headersStrings, "\r\n");

        for (auto& header : headers) {
            std::vector<std::string> headerParts = exolix::str::StringModify::split(header, ": ");
            if (headerParts.size() == 2) {
                this->headers[headerParts[0]] = headerParts[1];
            }
        }
    }

    std::string HttpHeaders::get(const std::string &key) {
        return headers[key];
    }

    void HttpHeaders::set(const std::string &key, const std::string &value) {
        headers[key] = value;
    }

    std::string HttpHeaders::toString() {
        std::string result;

        for (auto& header : headers) {
            result += header.first + ": " + header.second + "\r\n";
        }

        return result;
    }

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
