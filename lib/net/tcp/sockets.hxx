#pragma once

#include "../address.hxx"
#include <functional>
#include "system/unix.hxx"
#include "system/windows.hxx"
#include <map>
#include <tuple>
#include <thread>

namespace exolix {
    class Socket {

    };

    class SocketServer {
    private:
#if defined(__linux__) || defined(__APPLE__)
        UnixTcpServer *unixCoreServer = nullptr;
#elif defined(_WIN32)
        WinsockTcpServer *winsockCoreServer = nullptr;
#endif

        std::function<void(Socket &socket)> onAccept = [] (Socket &socket) {};
        std::function<void(int socketFd)> onPending = [] (int socketFd) {};

        std::map<std::thread, std::tuple<bool, uint64_t>> threads {};

        NetAddress &address;

        void onSocketInternal(uint64_t socketFd);

    public:
        explicit SocketServer(NetAddress &address);
        ~SocketServer();

        void listen();
        void stop();
        void block();

        void setOnAcceptListener(std::function<void(Socket &socket)> listener);
        void setOnPendingListener(std::function<void(int socketFd)> listener);
    };
}
