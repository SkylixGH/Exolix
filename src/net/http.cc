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
                initialLine = header;
                initial = false;

                std::vector<std::string> initialLineParts = exolix::str::StringModify::split(initialLine, " ");

                if (initialLineParts.size() == 3) {
                    std::string textMethod = str::StringModify::toUpper(initialLineParts[0]);

                    if (textMethod == "POST") method = HttpMethod::POST;
                    else if (textMethod == "PUT") method = HttpMethod::PUT;
                    else if (textMethod == "DELETE") method = HttpMethod::DELETE;
                    else if (textMethod == "GET") method = HttpMethod::GET;
                    else method = HttpMethod::NONE;

                    setPath(initialLineParts[1]);
                    std::string textVersion = str::StringModify::toUpper(initialLineParts[2]);

                    if (textVersion == "HTTP/1.0") httpVersion = HttpVersion::HTTP_1_0;
                    else httpVersion = HttpVersion::HTTP_1_1;
                } else if (initialLineParts.size() == 1) {
                    std::string textVersion = str::StringModify::toUpper(initialLineParts[2]);

                    if (textVersion == "HTTP/1.0") httpVersion = HttpVersion::HTTP_1_0;
                    else httpVersion = HttpVersion::HTTP_1_1;
                }
            }

            std::vector<std::string> keyPair = exolix::str::StringModify::split(header, ": ");
            if (keyPair.size() == 2) set(keyPair[0], keyPair[1]);
        }

        applyInitialLine();
    }

    std::string HttpHeaders::get(const std::string &key) {
        return headersUnordered[key];
    }

    std::string HttpHeaders::getInitialLine() {
        applyInitialLine();
        return initialLine;
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

    void HttpHeaders::setMethod(HttpMethod inMethod) {
        this->method = inMethod;
    }

    void HttpHeaders::setVersion(exolix::net::HttpVersion inVersion) {
        this->httpVersion = inVersion;
    }

    HttpMethod HttpHeaders::getMethod() {
        return method;
    }

    std::string HttpHeaders::getPath() {
        return path;
    }

    HttpVersion HttpHeaders::getVersion() {
        return httpVersion;
    }

    void HttpHeaders::setPath(const std::string &inPath) {
        this->path = inPath;
    }

    void HttpHeaders::applyInitialLine() {
        std::stringstream ss;

        const std::string methodString = getMethodString();
        const std::string versionString = getVersionString();

        if (!path.empty() && !methodString.empty())
            ss << methodString << " " << path << " " << versionString;
        else ss << versionString;

        initialLine = ss.str();
    }

    std::string HttpHeaders::getVersionString() {
        switch (httpVersion) {
            case HttpVersion::HTTP_1_0: return "HTTP/1.0";
            case HttpVersion::HTTP_1_1: return "HTTP/1.1";
        }
    }

    std::string HttpHeaders::getMethodString() {
        switch (method) {
            case HttpMethod::POST: return "POST";
            case HttpMethod::GET: return "GET";
            case HttpMethod::PUT: return "PUT";
            case HttpMethod::DELETE: return "DELETE";
            case HttpMethod::NONE: return "";
        }
    }

    std::string HttpHeaders::toString() {
        std::string result = getInitialLine() + "\r\n";

        for (auto &header : headersKeys) {
            auto pair = headersUnordered.find(header);
            result += header + ": " + pair->second + "\r\n";
        }

        return result;
    }

    HttpServer::HttpServer(int port) {
        server = new SocketServer(port);

        server->setOnSocketOpen([] (int id) {
            Socket socket(id);

            socket.setOnMessage([&socket] (SocketMessage *message) {
                HttpHeaders inputRequestHeaders(message->toString());
                HttpHeaders responseHeaders;

                responseHeaders.set("Host", "localhost:8080");
                responseHeaders.set("Cache-Control", "no-cache");
                responseHeaders.set("Content-Type", "text/html; charset=UTF-8");

                responseHeaders.setPath("");
                responseHeaders.setMethod(HttpMethod::NONE);
                responseHeaders.setVersion(inputRequestHeaders.getVersion());

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
                cout << "URL: " << inputRequestHeaders.getPath() << " method " << inputRequestHeaders.getMethodString() << " version " << inputRequestHeaders.getVersionString() << endl;

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
