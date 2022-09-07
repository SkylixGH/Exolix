#pragma once

#include <string>
#include <functional>
#include <exolix.hxx>

#if defined(__linux__) || defined(__APPLE__)
#include <sys/socket.h>
#include <netinet/in.h>
#endif

namespace exolix {
    enum class UnixTcpServerErrors {
        ADDRESS_PROTECTED_PERM_DENIED,
        ADDRESS_IN_USE,
        ADDRESS_INVALID,
        OTHER_SYS_ERROR
    };

    typedef Error<UnixTcpServerErrors> UnixTcpServerException;

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

        static void close(int socketFd);
        static void send(int socketFd, char buffer[], uint16_t length);
#endif
    };
}