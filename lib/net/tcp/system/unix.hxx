#pragma once

#include <string>
#include <functional>

#if defined(__linux__) || defined(__APPLE__)
#include <sys/socket.h>
#include <netinet/in.h>
#endif

namespace exolix {
    class UnixTcpServer {
    private:
#if defined(__linux__) || defined(__APPLE__)
        bool tlsOn = false;
        bool connectable = false;

        std::string tlsCert;
        std::string tlsKey;

        int socketFd;
        int clientFd;
        int clientLen;

        struct sockaddr_in clientAddress;
        struct sockaddr_in serverAddress;

        std::function<void(int &socketFd)> connectionHandler;

        void setupSocket();
        void setupAddress(uint16_t port, const std::string &host);
        void setupBinding();
#endif

    public:
#if defined(__linux__) || defined(__APPLE__)
        explicit UnixTcpServer(const std::function<void(int socketFd)> &connectionHandlerCallback);
        ~UnixTcpServer();

        void setTls(bool tls);
        void setCert(std::string cert);
        void setKey(std::string key);

        void listen(const std::string &address, uint16_t port);
        void halt();

        void close(int socketFd);
        void send(int socketFd, char buffer[], uint16_t length);
#endif
    };
}
