#pragma once

#include <string>
#include <functional>
#include "../../../error/error.hxx"
#include "../../../types.hxx"

#if defined(__linux__) || defined(__APPLE__)

#include <sys/socket.h>
#include <netinet/in.h>
#include <thread>
#include <map>
#include <openssl/ssl.h>

#endif

namespace exolix {
    enum class UnixTcpServerErrors {
        ADDRESS_PROTECTED_PERM_DENIED,
        ADDRESS_IN_USE,
        ADDRESS_INVALID,
        OTHER_SYS_ERROR,
        SSL_CERTIFICATE_LD_ERROR,
        SSL_KEY_LD_ERROR
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

        std::map<int, std::thread *> clientThreads;
        std::map<int, SSL *> clientSslGroup;

        SSL_CTX *sslCtx;

        void setupSocket();

        void setupAddress(u16 port, const std::string &host);

        void setupBinding();
#endif

    public:
#if defined(__linux__) || defined(__APPLE__)

        explicit UnixTcpServer(const std::function<void(int socketFd)> &connectionHandlerCallback);

        ~UnixTcpServer();

        void setTls(bool tls);

        void setCert(std::string certSsl);

        void setKey(std::string keySsl);

        void listen(const std::string &address, u16 port);

        void halt();

        static void close(int socketFd);

        static void send(int socketFd, char buffer[], u16 length);

        void cleanThread(int socketFdNotThis);

        std::optional<std::thread *> getThread(int socketFdNotThis);

        size_t countThreads();

        std::map<int, SSL *> getSslGroup();
#endif
    };
}
