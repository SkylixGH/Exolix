#pragma once

#include <string>
#include <thread>
#include <map>
#include <functional>
#include <exolix/err.h>
#include <openssl/ssl.h>

namespace exolix::net {
    class SocketServer;

    enum class SocketErrors {
        ServerPreviouslyStartedBefore,
        ServerCannotCreateSocket,
        ServerIsNotOnlineYet,
        ServerCannotListen,
        ServerCannotBind,
        ServerCannotSetSocketOptions,
        ServerInvalidHost,
        ServerCannotStartWsa,
        ServerCannotInteractUnlessManager
    };

    typedef err::Error<SocketErrors> SocketError;

    struct SocketMessage {
        char *data;
        size_t size;

        [[nodiscard]] std::string toString() const;
    };

    struct SocketServerOptions {
        unsigned int backlog = 128;
        std::string certificateAt;
        std::string keyAt;
    };

    class SocketInternalManager {
    private:
        static bool osslDoneBefore;

    public:
        static void initOssl();
        static void cleanupOssl();
    };

    class Socket {
    private:
        int socketHandle;
        bool running = true;
        SSL *clientTls = nullptr;

        std::thread *thread = nullptr;
        SocketServer &serverRef;

        std::function<void(SocketMessage &)> onMessage = [] (SocketMessage &message) {};

        void ld();

    public:
        explicit Socket(int osHandle, SocketServer &server);
        Socket(int osHandle, SocketServer &server, SSL *ssl);

        ~Socket();

        void block();
        void close();

        void send(const SocketMessage &message) const;
        void send(const std::string &message) const;

        void setOnMessageListener(const std::function<void(SocketMessage &)> &listener);
    };

    class SocketAbstractManager {
    public:
        virtual void handleConnect(Socket &socket);
    };

    class SocketServer {
    private:
        bool hasStartedBefore = false;
        bool hasShutdownBefore = false;
        bool isListening = false;
        bool isTls = false;
        int osSocketHandle {};

        SSL_CTX *tlsContext = nullptr;

        uint16_t port {};
        std::string host;
        SocketServerOptions options;

        std::thread *thread = nullptr;
        std::map<int, Socket&> sockets;

        SocketAbstractManager *manager = nullptr;

        std::function<void(Socket &)> onSocketOpen = [] (Socket &socket) {};

    public:
        explicit SocketServer(SocketServerOptions options = {});
        ~SocketServer();

        void listen(uint16_t listeningPort, const std::string &listeningHost = "0.0.0.0");
        void shutdown();

        void block();

        void destroySocketManager();
        void setSocketManager(SocketAbstractManager &managerInput);

        void setOnSocketOpenListener(const std::function<void(Socket &)> &listener);
    };
}
