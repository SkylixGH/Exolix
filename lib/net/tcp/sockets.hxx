#pragma once

#include "../address.hxx"
#include "../../error/error.hxx"
#include <functional>
#include "system/unix.hxx"
#include "system/windows.hxx"
#include <map>
#include <tuple>
#include <thread>

namespace exolix {
    enum class SocketServerErrors {
        LISTEN_FAILED,
        INVALID_ADDRESS_HOST
    };

    typedef Error<SocketServerErrors> SocketServerException;

    class SocketMessage {
    private:
        char *data;

    public:
        const u16 size;

        SocketMessage(std::string messageString);
        SocketMessage(char buffer[], u16 length);

        std::string toString();
        char *getData();
    };

    class Socket {
    private:
        u64 socketFd;
        std::function<void(SocketMessage &)> onReceive = [] (SocketMessage &message) {};

        bool open = true;

        std::thread *thread;

    public:
        const std::string clientIp;

        Socket(u64 fd);
        ~Socket();

        void send(SocketMessage messageRaw);
        void send(std::string messageString);

        void close();
        bool isOpen();

        void setOnReceiveListener(std::function<void(SocketMessage &)> listener);

        void block();

         u64 getSocketFd();
    };

    class SocketServer {
    private:
#if defined(__linux__) || defined(__APPLE__)
        UnixTcpServer *unixCoreServer = nullptr;
#elif defined(_WIN32)
        WinsockTcpServer *winsockCoreServer = nullptr;
#endif

        std::function<void(Socket &socket)> onAccept = [] (Socket &socket) {};

        std::map<std::thread, std::tuple<bool, u64>> threads{};

        NetAddress &address;

        void onSocketInternal(u64 socketFd);

    public:
        explicit SocketServer(NetAddress &address);

        ~SocketServer();

        void listen();

        void stop();

        void block();

        void setOnAcceptListener(std::function<void(Socket &socket)> listener);
    };
}
