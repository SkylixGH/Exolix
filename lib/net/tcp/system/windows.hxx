#pragma once

#include "../../../error/error.hxx"

#ifdef _WIN32
#include <string>
#include <functional>
#include <winsock2.h>
#include <process.h>
#include "../../../types.hxx"
#endif

namespace exolix {
    enum class WinsockTcpServerErrors {
        FAILED_TO_LISTEN,
        STARTUP_FAIL,
        ADDRESS_INFO_ERROR,
        SOCKET_CREATE_FAIL,
        ADDRESS_IN_USE,
        PERMISSION_ERROR,
        BINDING_ERROR
    };

    typedef Error<WinsockTcpServerErrors> WinsockTcpServerException;

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
        void configureAddress(const std::string &host, u16 port);
        void init();

        std::string getLastError();

        bool pendingSocket = false;
#endif

    public:
#if _WIN32
        explicit WinsockTcpServer(std::function<void(SOCKET socketFd)> connectionHandlerCallback);
        ~WinsockTcpServer();

        void listen(const std::string &address, u16 port);
        void halt();

        void setTls(bool tls);
        void setCert(std::string certPath);
        void setKey(std::string keyPath);

        SOCKET getRecentSocket();
        bool isSocketCurrentlyPending();

        static void close();
        static void send(char buffer[65535]);

        static void close(SOCKET socketFd);
        static void send(SOCKET socketFd, char buffer[], u16 length);
#endif
    };
}
