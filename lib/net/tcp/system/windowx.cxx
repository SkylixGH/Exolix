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
                        WinsockTcpServerErrors::BINDING_ERROR,
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

    void WinsockTcpServer::configureAddress(const std::string &host, u16 port) {
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

    void WinsockTcpServer::listen(const std::string &address, u16 port) {
        if (tls) {
            // TODO: Make this system global and use signal();
            SSL_load_error_strings();
            SSL_library_init();
            OpenSSL_add_all_algorithms();
        }

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

            sslCtx = SSL_CTX_new(SSLv23_server_method());
            SSL_CTX_set_options(sslCtx, SSL_OP_SINGLE_DH_USE);

            int cert = SSL_CTX_use_certificate_file(sslCtx, certSsl.c_str(), SSL_FILETYPE_PEM);
            int key = SSL_CTX_use_PrivateKey_file(sslCtx, keySsl.c_str(), SSL_FILETYPE_PEM);

            if (cert != 1)
                throw WinsockTcpServerException(
                    WinsockTcpServerErrors::SSL_CERTIFICATE_LD_ERROR,
                    "Failed to load SSL certificate, with the following error code: " + std::to_string(cert)
                );

            if (key != 1)
                throw WinsockTcpServerException(
                    WinsockTcpServerErrors::SSL_KEY_LD_ERROR,
                    "Failed to load the SSL key, with the following erorr code: " + std::to_string(key)
                );

            do {
                clientSocket = ::accept(serverSocket, nullptr, nullptr);

                if (clientSocket != INVALID_SOCKET) {
                    unsigned threadId;
                    pendingSocket = true;

                    _beginthreadex(
                        NULL,
                        0,
                        [] (void *arg) -> unsigned {
                            auto *server = (WinsockTcpServer *) arg;

                            SSL *clientSsl = SSL_new(server->sslCtx);
                            SSL_set_fd(clientSsl, server->clientSocket);

                            SSL_accept(clientSsl);

                            server->sslSockets.insert(std::pair<SOCKET, SSL *>(server->clientSocket, clientSsl));

                            server->pendingSocket = false;
                            server->connectionHandler(server->clientSocket);

                            SSL_free(clientSsl);
                            server->sslSockets.erase(server->clientSocket);
                            return 0;
                        },
                        this,
                        0,
                        &threadId
                    );

                    while (pendingSocket) {}
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
        this->certSsl = certPath;
    }

    void WinsockTcpServer::setKey(std::string keyPath) {
        this->keySsl = keyPath;
    }

    std::map<SOCKET, SSL *> WinsockTcpServer::getTlsClients() {
        return sslSockets;
    }
#endif
}
