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
        WSADATA wsaData;

        bool online = false;
        bool tls = false;

        struct addrinfo *result = NULL;
        struct addrinfo hints;

        int intResult;

        std::string cert;
        std::string key;

        SOCKET serverSocket = INVALID_SOCKET;
        SOCKET clientSocket = INVALID_SOCKET;

        void bind();
        void configureAddress();
        void init();
#endif

    public:
#if _WIN32
        explicit WinsockTcpServer(const std::function<void(SOCKET socketFd)> &connectionHandlerCallback);
        ~WinsockTcpServer();

        void listen(const std::string &address, uint16_t port);
        void halt();

        void setTls(bool tls);
        void setCert(std::string cert);
        void setKey(std::string key);

        static void close();
        static void send(char buffer[65535]);
#endif
    };
}
