#include <sstream>
#include "http.h"
#include <exolix/str.h>
#include <vector>
#include <string>

#include <iostream>
using namespace std;

namespace exolix::net {
    HttpHeaders::HttpHeaders() = default;

    HttpHeaders::HttpHeaders(const std::string &headersStrings) {
        std::vector<std::string> headers = exolix::str::StringModify::split(headersStrings, "\r\n");

        bool initial = true;

        for (auto& header : headers) {
            if (initial) {
                writeInitialLine(header);
                initial = false;
            }

            std::vector<std::string> keyPair = exolix::str::StringModify::split(header, ": ");
            if (keyPair.size() == 2) set(keyPair[0], keyPair[1]);
        }
    }

    void HttpHeaders::writeInitialLine(const std::string &line) {
        httpInitialLine = line;
    }

    std::string HttpHeaders::get(const std::string &key) {
        return headersUnordered[key];
    }

    std::string HttpHeaders::getInitialLine() {
        return httpInitialLine;
    }

    void HttpHeaders::set(const std::string &key, const std::string &value) {
        if (key.empty()) return;

        if (headersUnordered.find(key) != headersUnordered.end()) {
            headersUnordered[key] = value;
        } else {
            headersKeys.push_back(key);
            headersUnordered.insert({ key, value });
        }
    }

    void HttpHeaders::remove(const std::string &key) {
        headersUnordered.erase(key);
        headersKeys.erase(std::find(headersKeys.begin(), headersKeys.end(), key));
    }

    std::string HttpHeaders::toString() {
        std::string result = httpInitialLine + "\r\n";

        for (auto &header : headersKeys) {
            auto pair = headersUnordered.find(header);
            result += header + ": " + pair->second + "\r\n";
        }

        return result;
    }

    HttpServer::HttpServer(int port) {
        server = new SocketServer(port);

        server->setOnSocketOpen([this] (int id) {
            Socket socket(id);

            socket.setOnMessage([this, &socket] (SocketMessage *message) {
                HttpHeaders inputRequestHeaders(message->toString());
                HttpHeaders responseHeaders;

                responseHeaders.set("Host", "localhost:8080");
                responseHeaders.set("Cache-Control", "no-cache");
                responseHeaders.set("Content-Type", "text/html; charset=UTF-8");

//                socket.send(
//                        "HTTP/1.1 200 OK\r\n"
//                        "Host: localhost:8080\r\n"
//                        "Content-Type: text/html; charset=UTF-8\r\n"
//                        "Cache-Control: no-cache\r\n"
//                        "\r\n"
//                        "<html><body>Hello World!</body></html>"
//                );

                socket.send(
                        responseHeaders.toString() +
                        "\r\n"
                        "<html><body><pre>" + inputRequestHeaders.toString() + "</pre></body></html>"
                );

                std::string url = inputRequestHeaders.get("url");
                cout << "URL: " << inputRequestHeaders.getInitialLine() << endl;

                socket.close();
            });

            socket.block();
        });
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
