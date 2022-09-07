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
        std::function<void(u64 socketFd)> onPending = [] (u64 socketFd) {};

        std::map<std::thread, std::tuple<bool, u64>> threads {};

        NetAddress &address;

        void onSocketInternal(u64 socketFd);

    public:
        explicit SocketServer(NetAddress &address);
        ~SocketServer();

        void listen();
        void stop();
        void block();

        void setOnAcceptListener(std::function<void(Socket &socket)> listener);
        void setOnPendingListener(std::function<void(u64 socketFd)> listener);
    };
}
