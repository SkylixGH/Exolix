#include "windows.hxx"
#include <string>
#ifdef _WIN32
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iostream>
#include <utility>
#include <thread>
#endif

// TODO: Remove 
#include <iostream>

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
            switch (WSAGetLastError()) {
                case WSANOTINITIALISED:
                case WSAENETDOWN:
                    throw WinsockTcpServerException(
                        WinsockTcpServerErrors::BINDING_FAILER,
                        "Winsock failed to bind the server due to an unexpected error, winsock returned error code: " + std::to_string(WSAGetLastError())
                    );

                case WSAEADDRINUSE:
                    throw WinsockTcpServerException(
                        WinsockTcpServerErrors::ADDRESS_IN_USE,
                        "Winsock failed to bind the server due to the address being in use"
                    );

                case WSAEACCES:
                    throw WinsockTcpServerException(WinsockTcpServerErrors::PERMISSION_ERROR, "Winsock failed to bind the server due to insufficient permissions");
            }
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
            switch (WSAGetLastError()) {
                case WSATRY_AGAIN:
                case WSAEINVAL:
                case WSANO_RECOVERY:
                case WSAEAFNOSUPPORT:
                case WSA_NOT_ENOUGH_MEMORY:
                case WSAHOST_NOT_FOUND:
                case WSATYPE_NOT_FOUND:
                case WSAESOCKTNOSUPPORT:
                    throw WinsockTcpServerException(WinsockTcpServerErrors::ADDRESS_INFO_ERROR, "An unknown system error occurred while setting up address with error code: " + std::to_string(WSAGetLastError()));
            }
        }

        serverSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
        if (serverSocket == INVALID_SOCKET) {
            switch (WSAGetLastError()) {
                case WSANOTINITIALISED:
                case WSAENETDOWN:
                case WSAEAFNOSUPPORT:
                case WSAEINPROGRESS:
                case WSAEMFILE:
                case WSAEINVAL:
                case WSAEINVALIDPROVIDER:
                case WSAEINVALIDPROCTABLE:
                case WSAENOBUFS:
                case WSAEPROTONOSUPPORT:
                case WSAEPROTOTYPE:
                case WSAEPROVIDERFAILEDINIT:
                case WSAESOCKTNOSUPPORT:
                    throw WinsockTcpServerException(WinsockTcpServerErrors::SOCKET_CREATE_FAIL, "An unknown system error occurred while creating socket with error code: " + std::to_string(WSAGetLastError()));
            }
        }
    }

    void WinsockTcpServer::init() {
        intResult = WSAStartup(MAKEWORD(2, 2), &wsaData);

        if (intResult != 0) {
            switch (WSAGetLastError()) {
                case WSASYSNOTREADY:
                case WSAVERNOTSUPPORTED:
                case WSAEINPROGRESS:
                case WSAEPROCLIM:
                case WSAEFAULT:
                    throw WinsockTcpServerException(WinsockTcpServerErrors::STARTUP_FAIL, "An unknown system error occurred while starting up WSA with error code: " + std::to_string(WSAGetLastError()));
            } 
        }
    }

    void WinsockTcpServer::halt() {
        online = false;
        closesocket(serverSocket);
        WSACleanup();
    }

    void WinsockTcpServer::listen(const std::string &address, uint16_t port) {
        std::thread thread([this, &address, &port] () {
            init();
            configureAddress(address, port);
            bind();

            freeaddrinfo(result);
            intResult = ::listen(serverSocket, SOMAXCONN);

            if (intResult == SOCKET_ERROR) {
                switch (WSAGetLastError()) {
                    case WSANOTINITIALISED:
                    case WSAENETDOWN:
                    case WSAEINPROGRESS:
                    case WSAEINVAL:
                    case WSAEISCONN:
                    case WSAEMFILE:
                    case WSAENOBUFS:
                    case WSAENOTSOCK:
                    case WSAEOPNOTSUPP:
                        throw WinsockTcpServerException(
                            WinsockTcpServerErrors::FAILED_TO_LISTEN,
                            "Could not listen on server due to an unexpected error, winsock returned the following error: " + std::to_string(WSAGetLastError())
                        );

                    case WSAEADDRINUSE:
                        throw WinsockTcpServerException(
                            WinsockTcpServerErrors::ADDRESS_IN_USE,
                            "The address requested to be used with the server could not be used as it is already in use"
                        );
                }
            } else {
                online = true;
            }

            do {
                clientSocket = ::accept(serverSocket, nullptr, nullptr);

                if (clientSocket != INVALID_SOCKET) {
                    unsigned threadId;
                    HANDLE clientThreadProcess = (HANDLE) _beginthreadex(
                        NULL, 
                        0, 
                        [](void *arg) -> unsigned {
                            SOCKET socket = (SOCKET) arg;

                            while (true) {
                                char buffer[1024];
                                int bytesReceived = recv(socket, buffer, 1024, 0);

                                if (bytesReceived > 0) {
                                    std::cout << "Received: " << buffer << std::endl;
                                } else if (bytesReceived == 0) {
                                    std::cout << "Client disconnected" << std::endl;
                                    break;
                                } else {
                                    std::cout << "recv failed with error: " << WSAGetLastError() << std::endl;
                                    break;
                                }
                            }
                            return 0;
                        },
                        (void*) clientSocket, 
                        0, 
                        &threadId
                    );
                } else {
                    std::cerr << "Failed\n";
                }
            } while (online);
        });

        thread.join();
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
        closesocket(socketFd);
    }

    void WinsockTcpServer::send(SOCKET socketFd, char buffer[], uint16_t length) {
        int bytesSent = ::send(socketFd, buffer, length, 0);
        
        if (bytesSent == SOCKET_ERROR) {
            
        }
    }
#endif
}
