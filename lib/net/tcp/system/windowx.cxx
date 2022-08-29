#include "windows.hxx"
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iostream>

namespace exolix {
    WinsockTcpServer::WinsockTcpServer(std::function<void(SOCKET socketFd)> connectionHandlerCallback):
        connectionHandler(connectionHandlerCallback) {
    }

    WinsockTcpServer::~WinsockTcpServer() {

    }

    void WinsockTcpServer::bind() {
        intResult = ::bind(serverSocket, result->ai_addr, (int) result->ai_addrlen);
        if (intResult == SOCKET_ERROR) {
            exit(567878711);
        }
    }

    void WinsockTcpServer::configureAddress() {
        ZeroMemory(&hints, sizeof(hints));

        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_STREAM;
        hints.ai_protocol = IPPROTO_TCP;
        hints.ai_flags = AI_PASSIVE;

        intResult = getaddrinfo("127.0.0.1", "8080", &hints, &result);
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
        configureAddress();
        bind();

        freeaddrinfo(result);
        intResult = ::listen(serverSocket, SOMAXCONN);

        if (intResult == SOCKET_ERROR) {
            exit(567878711);
        } else {
            online = true;
        }

        do {
            clientSocket = ::accept(serverSocket, NULL, NULL);
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

    void WinsockTcpServer::setCert(std::string cert) {
        this->cert = cert;
    }

    void WinsockTcpServer::setKey(std::string key) {
        this->key = key;
    }
}
