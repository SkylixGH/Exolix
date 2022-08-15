#pragma once

#include <string>
#include <thread>
#include <map>
#include <functional>
#include <exolix/err.h>

namespace exolix::net {
    enum class SocketErrors {
        ServerPreviouslyStartedBefore,
        ServerCannotCreateSocket,
        ServerIsNotOnlineYet,
        ServerCannotListen,
        ServerCannotBind,
        ServerCannotSetSocketOptions,
        ServerInvalidHost,
    };

    typedef err::Error<SocketErrors> SocketError;

    struct SocketMessage {
        char *data;
        int size;

        [[nodiscard]] std::string toString() const;
    };

    struct SocketServerOptions {
        int backlog = 128;
    };

    class Socket {
    private:
        int socketHandle;
        bool running = true;
        std::thread thread;

    public:
        explicit Socket(int osHandle);

        void block();
        void close();

        void send(const std::string &message);
        void send(const SocketMessage &message);
    };

    class SocketServer {
    private:
        bool hasStartedBefore = false;
        bool hasShutdownBefore = false;
        bool isListening = false;
        int osSocketHandle {};

        uint16_t port {};
        std::string host;
        SocketServerOptions options;

        std::thread *thread = nullptr;
        std::map<int, Socket&> sockets;

        std::function<void(Socket &)> onSocketOpen = [] (Socket &socket) {};

    public:
        explicit SocketServer(const SocketServerOptions &options = {});
        ~SocketServer();

        void listen(uint16_t listeningPort, const std::string &listeningHost = "0.0.0.0");
        void shutdown();

        void block();

        void setOnSocketOpenListener(const std::function<void(Socket &)> &listener);
    };
}
