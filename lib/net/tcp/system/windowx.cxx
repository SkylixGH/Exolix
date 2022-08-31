#include "windows.hxx"
#ifdef _WIN32
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iostream>
#include <utility>
#endif

namespace exolix {
#ifdef _WIN32
    WinsockTcpServer::WinsockTcpServer(std::function<void(SOCKET socketFd)> connectionHandlerCallback):
        connectionHandler(std::move(connectionHandlerCallback)) {
    }

    WinsockTcpServer::~WinsockTcpServer() {
        // TODO: Clean up
    }

    void WinsockTcpServer::bind() {
        intResult = ::bind(serverSocket, result->ai_addr, (int) result->ai_addrlen);
        if (intResult == SOCKET_ERROR) {
            exit(567878711);
        }
    }

    void WinsockTcpServer::configureAddress(const std::string &host, uint16_t port) {
        ZeroMemory(&hints, sizeof(hints));

        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_STREAM;
        hints.ai_protocol = IPPROTO_TCP;
        hints.ai_flags = AI_PASSIVE;

        intResult = getaddrinfo(host.c_str(), std::to_string(port).c_str(), &hints, &result);
        if (intResult != 0) {
            exit(66834);
        }

        serverSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
        if (serverSocket == INVALID_SOCKET) {
            exit(64835);
        }
    }

    void WinsockTcpServer::init() {
        intResult = WSAStartup(MAKEWORD(2, 2), &wsaData);

        if (intResult != 0) {
            exit(6654);
        }
    }

    void WinsockTcpServer::halt() {
        online = false;
        closesocket(serverSocket);
        WSACleanup();
    }

    void WinsockTcpServer::listen(const std::string &address, uint16_t port) {
        init();
        configureAddress(address, port);
        bind();

        freeaddrinfo(result);
        intResult = ::listen(serverSocket, SOMAXCONN);

        if (intResult == SOCKET_ERROR) {
            exit(567878711);
        } else {
            online = true;
        }

        do {
            clientSocket = ::accept(serverSocket, nullptr, nullptr);

            if (clientSocket != INVALID_SOCKET) {
                connectionHandler(clientSocket);
            } else {
                std::cerr << "Failed\n";
            }
        } while (online);
    }

    void WinsockTcpServer::setTls(bool tls) {
        this->tls = tls;
    }

    void WinsockTcpServer::setCert(std::string certPath) {
        this->cert = certPath;
    }

    void WinsockTcpServer::setKey(std::string keyPath) {
        this->key = keyPath;
    }

    void WinsockTcpServer::close(SOCKET socketFd) {
        try {
            closesocket(socketFd);
        } catch (...) {
            // ignore
        }
    }
#endif
}
