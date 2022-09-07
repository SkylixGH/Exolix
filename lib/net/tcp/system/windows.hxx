#pragma once

#ifdef _WIN32
#include <string>
#include <functional>
#include <winsock2.h>
#endif

namespace exolix {
    class WinsockTcpServer {
    private:
#ifdef _WIN32
        std::function<void(SOCKET socketFd)> connectionHandler;

        WSADATA wsaData {};

        bool online = false;
        bool tls = false;

        struct addrinfo *result = nullptr;
        struct addrinfo hints {};

        int intResult = 0;

        std::string cert;
        std::string key;

        SOCKET serverSocket = INVALID_SOCKET;
        SOCKET clientSocket = INVALID_SOCKET;

        void bind();
        void configureAddress(const std::string &host, uint16_t port);
        void init();
#endif

    public:
#if _WIN32
        explicit WinsockTcpServer(std::function<void(SOCKET socketFd)> connectionHandlerCallback);
        ~WinsockTcpServer();

        void listen(const std::string &address, uint16_t port);
        void halt();

        void setTls(bool tls);
        void setCert(std::string certPath);
        void setKey(std::string keyPath);

        static void close();
        static void send(char buffer[65535]);

        int getActiveSockets();

        static void close(SOCKET socketFd);
        static void send(SOCKET socketFd, char buffer[], uint16_t length);
#endif
    };
}
