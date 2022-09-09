#pragma once

#include "../address.hxx"
#include "../../error/error.hxx"
#include <functional>
#include "system/unix.hxx"
#include "system/windows.hxx"
#include <map>
#include <tuple>
#include <thread>
#include <optional>
#include <string>
#include <openssl/ssl.h>

namespace exolix {
    enum class SocketServerErrors {
        LISTEN_FAILED,
        INVALID_ADDRESS_HOST
    };

    typedef Error<SocketServerErrors> SocketServerException;

    struct SocketTlsConfiguration {
        std::string cert = "";
        std::string key = "";
    };

    class SocketMessage {
    private:
        char *data;

    public:
        const u16 size;

        SocketMessage(const std::string& messageString);
        SocketMessage(char buffer[], u16 length);

        std::string toString();
        char *getData();
    };

    class Socket {
    private:
        u64 socketFd;
        std::function<void(SocketMessage &)> onReceive = [] (SocketMessage &message) {};

        bool open = true;
        bool tls;

        std::optional<SSL *> sslClient;

        std::thread *thread;
        std::string clientIp;

    public:
        Socket(u64 fd, std::optional<SSL *> ssl);
        ~Socket();

        void send(SocketMessage messageRaw);
        void send(const std::string& messageString);

        void close();
        bool isOpen() const;

        void setOnReceiveListener(std::function<void(SocketMessage &)> listener);

        void block();

        u64 getSocketFd() const;
        std::string getClientIp();
    };

    class SocketServer {
    private:
#if defined(__linux__) || defined(__APPLE__)
        UnixTcpServer *unixCoreServer = nullptr;
#elif defined(_WIN32)
        WinsockTcpServer *winsockCoreServer = nullptr;
#endif

        std::function<void(Socket &socket)> onAccept = [] (Socket &socket) {};

        std::thread *thread = nullptr;

        NetAddress &address;

        void onSocketInternal(u64 socketFd);

        bool tls = false;

        SocketTlsConfiguration tlsConfiguration {};

    public:
        explicit SocketServer(NetAddress &address);

        ~SocketServer();

        void listen();

        void stop();

        void block();

        void setTls(std::false_type tlsInput);

        void setTls(SocketTlsConfiguration configuration);

        void setOnAcceptListener(std::function<void(Socket &socket)> listener);
    };
}
