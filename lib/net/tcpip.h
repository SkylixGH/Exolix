#pragma once

#include "../number/types.h"
#include "address.h"
#include "../thread/thread.h"
#include <functional>
#include <optional>
#include <openssl/ssl.h>
#include <map>

#if defined(_WIN32)

    #include <windows.h>

#endif

namespace exolix {
    /**
     * An enumerated list of possible error codes from members of
     * the socket server adapter.
     */
    enum class SocketServerAdapterErrors {
        /**
         * No errors occurred. The action has completed
         */
        Ok,

        /**
         * The socket thread was queued to be blocked
         * but it was already blocked. The block must
         * only be performed once.
         */
        SocketAlreadyBlocked,

        /**
         * The socket is not alive and this action
         * cannot be performed.
         */
        SocketNotAlive,

        /**
         * The server could not send a message to the client
         * for an unknown reason.
         */
        FailedToSendData
    };

    /**
     * This is an enumerated list of possible error codes
     * that can be returned by members of the socket server.
     */
    enum class SocketServerErrors {
        /**
         * No errors occurred. The action has completed
         * successfully.
         */
        Ok,

        /**
         * The server performed an action that required
         * the server to be actively online and not busy,
         * but the action was still requested to be performed.
         */
        ServerNotReadyForAction,

        /**
         * The server had an operation requested to be performed
         * on it, but the server was unsafe to operate on with this
         * specific job. The server must be inactive and not busy for
         * the action to be performed.
         */
        ServerDangerousActionWhileOnline,

        /**
         * The server was requested to be blocked but the server
         * has already previously been blocked, without experiencing
         * a restart. Please restart the server to block the server
         * process thread again.
         */
        CannotBlockServerAfterPreviouslyBlockedWithoutRestart,

        /**
         * The server was requested to work and set a TLS certificate
         * or key but the server did not have TLS mode enabled.
         */
        TlsNotEnabled,

        /**
         * The server address's port provided is faulty and cannot be used
         * to create a server.
         */
        FaultyAddressPort,

        /**
        * The server address's hostname provided is faulty and cannot be used
        * to create a server.
        */
        FaultyAddressHostname,

        /**
         * Another address error occurred. Please check the address
         * instance for more information.
         */
        AddressError,

        /**
         * Could not create server socket instance for the server for an
         * unknown reason.
         */
        CouldNotCreateServerSocketInstance,

        /**
         * A permission error occurred when creating the socket.
         */
        PermissionFaulty,

        /**
         * IPv4 or IPv6 is not supported on the system.
         */
        IpVersionNotSupported,

        /**
         * Too many descriptors are open on the system.
         */
        TooManyDescriptorsOpen,

        /**
         * The server does not have enough memory to start.
         */
        FaultyMemoryAccess,

        /**
         * The server could not set the socket option.
         */
        CouldNotSetSocketOption,

        /**
         * The server failed to resolved the hostname.
         */
        CouldNotResolveHostname,

        /**
         * The socket server failed to listen.
         */
        ListenFail,

        /**
        * The socket server failed to bind.
        */
        BindingFail,
    };

    /**
     * This is a format for storing messages
     * for sending between sockets.
     */
    struct SocketServerAdapterMessage {
        /**
         * The actual data contents.
         */
        char *data;

        /**
         * The size of the data.
         */
        i16 size;

        /**
         * Convert the data to a string.
         * @return The data as a string.
         */
        [[nodiscard]] std::string toString() const;
    };

    /**
     * This is a class instance of a socket server client connection.
     */
    class SocketServerAdapter {
    private:
        /**
         * The SSL client context. This is used for
         * TLS connections.
         */
        std::optional<SSL *> cSsl;

        /**
         * The socket file descriptor. This is used
         * for all socket connections.
         */
        u16 cSocket;

        /**
         * The client listening thread. This is used
         * for listening to the client socket. This thread is generally
         * only used when the server is running on Linux or Apple. Win32
         * uses its own threading system.
         */
        Thread *cThread{};

        /**
         * The client listening thread but for Win32. This is used
         * for listening to the client socket. This thread is generally
         * only used when the server is running on Win32. Linux and Apple
         * use its own threading system.
         */
        i64 cThreadWin32{};

        /**
         * Whether the socket is currently connected
         * to a client.
         */
        bool cConnected;

        /**
         * The IP address of the client.
         */
        std::string ip;

        /**
         * The handler for on message. This is called
         * when a message is received from the client.
         */
        std::function<void(SocketServerAdapterMessage &message)> onMessage = [](SocketServerAdapterMessage &message) {};

        /**
         * The handler for on disconnect. This is called
         * when the client disconnects from the server.
         */
        std::function<void()> onDisconnect = []() {};

        /**
         * This variable doesn't have a specific purpose
         * but it is used to block the outside of a thread
         * temporarily while the thread initializes some
         * variables and contexts.
         */
        bool waitingSocketJob;

        /**
         * The buffer write source, this is where data that
         * was read will be written to.
         */
        char *bufferWriteSource;

        /**
         * The buffer write source size, this is the size of
         * the buffer write source.
         */
        u16 bufferWriteSourceSize;

        /**
         * Whether the win32 thread was blocked before.
         */
        bool win32ThreadBlocked;

    public:
        /**
         * Create a new socket instance.
         */
        explicit SocketServerAdapter(u16 socketFd, std::optional<SSL *> ssl = std::nullopt, char *readBuffer = {},
                                     u16 readBufferSize = 1024);

        ~SocketServerAdapter();

        /**
         * Kill the socket connection if it has not been
         * killed and or is live and active.
         */
        void kill();

        /**
         * Get the client IP address.
         * @return The client IP address.
         */
        std::string getIp();

        /**
         * Set the socket message listener. This is called
         * when a message is received from the client.
         * @param listener The listener.
         */
        void setOnMessageListener(std::function<void(SocketServerAdapterMessage &message)> listener);

        /**
         * Set the socket disconnect listener. This is called
         * when the client disconnects from the server.
         * @param listener The listener.
         */
        void setOnDisconnectListener(std::function<void()> listener);

        /**
         * Block the current the thread until the socket
         * is closed or terminated.
         * @return Whether the socket is closed or not.
         */
        SocketServerAdapterErrors block();

        /**
         * Whether the win32 thread is finished yet.
         */
        bool win32ThreadOver;

        /**
         * Check to see if the client is active, connected and loaded.
         * Notice that this function will return inaccurate data if the thread
         * was not blocked first.
         * @return Whether the client is active or not.
         */
        [[nodiscard]] bool isActive() const;

        /**
         * Check to see whether the thread has been blocked
         * before.
         * @return If the thread has been blocked before.
         */
        [[nodiscard]] bool blockedBefore() const;

        /**
         * Send a socket message to the client.
         * @param message The message to send/
         * @return An error or Ok status code.
         */
        SocketServerAdapterErrors send(SocketServerAdapterMessage &message);

        /**
         * Send a socket message to the client as a string.
         * @param message The message to send.
         * @return An error or Ok status code.
         */
        SocketServerAdapterErrors send(const std::string &message);
    };

    /**
     * A TCP server implementation with cross-platform support
     * and TLS capabilities.
     */
    class SocketServer {
    private:
        /**
         * The address of the server. This is used for
         * binding the server to a specific address.
         */
        const NetAddress address;

        /**
         * The server thread for win32 systems. This is used
         */
        i64 win32ServerThread{};

        /**
         * The current external socket.
         */
        i64 extSocket{};

        /**
         * The message buffer system.
         */
        char *buffer{};

        /**
         * Whether the win32 thread is done. This will be inaccurate if the thread
         * was never blocked.
         */
        bool win32ThreadDone{};

        /**
         * The backlog of the server. A backlog number
         * is the maximum number of connections that can
         * be queued.
         */
        const int backlog;

        /**
         * The pool of client threads.
         */
        std::map<i64, Thread *> clientThreads{};

        /**
         * The pool of clients connected.
         */
        std::map<i64, SocketServerAdapter &> clients{};

        /**
         * Whether the server is online.
         */
        bool online;

        /**
         * Whether the server is using TLS.
         */
        bool tls;

        /**
         * Path to the TLS certificate.
         */
        std::string certificate;

        /**
         * Path to the TLS private key.
         */
        std::string privateKey;

        /**
         * Whether the server is busy. When busy the server
         * should not allow another load or unload action
         * while busy.
         */
        bool busy;

        /**
         * The server thread where all of the base processing will
         * be performed such as connection accepting and dispatching.
         */
        Thread *serverThread;

        /**
         * Receive buffer size, this is the maximum amount of data
         * in bytes that the server can read in a single message.
         */
        u16 receiveBufferSize;

        /**
         * The TLS context. This is used for TLS connections.
         * This is only used when TLS is enabled.
         */
        std::optional<SSL_CTX *> sslContext;

        /**
         * The socket file descriptor. This is used for all
         * socket connections.
         */
        u16 socketFd;

        /**
         * Connection on message listener. This event is triggered
         * when a new socket is ready and usable.
         */
        std::function<void(SocketServerAdapter &socket)> onSocket = [](SocketServerAdapter &) {};

        /**
         * The thread where automatic garbage collecting for
         * useless client threads will be performed.
         */
        Thread *trashThread;

        /**
         * Trash collection interval time, the amount of time to wait
         * before cleaning useless threads and clients.
         */
        u32 trashCollectionInterval;

        /**
         * Clean up the server variables after a crash
         * and reset all properties to defaults.
         */
        void cleanUp();

        /**
         * Collect garbage client threads.
         */
        void collectGarbage();

    public:
        /**
         * Create a new socket server.
         * @param address The address to bind to.
         * @param backlog The maximum amount of connections to queue.
         */
        SocketServer(NetAddress address, int backlog);

        ~SocketServer();

        /**
         * Set the TLS mode of the server.
         * @param enabled Whether to enable TLS.
         */
        SocketServerErrors setTls(bool enabled);

        /**
         * Set the TLS certificate of the server.
         * @param path The path to the certificate.
         */
        SocketServerErrors setCertificate(const std::string &path);

        /**
         * Set the TLS private key of the server.
         * @param path The path to the private key.
         */
        SocketServerErrors setPrivateKey(const std::string &path);

        /**
         * Set the on socket listener. This event is triggered
         * when a new socket is ready and usable.
         */
        void setOnSocketListener(const std::function<void(SocketServerAdapter &socket)> &listener);

        /**
         * Load the server. When loading, the server cannot perform any
         * other load or unload actions. It will be marked busy until
         * the function succeeds or fails.
         */
        SocketServerErrors load();

        /**
         * Unload the server. When unloading, the server cannot perform
         * any other load or unload actions. It will be marked busy until
         * the function succeeds or fails.
         */
        SocketServerErrors unload();

        /**
         * This will block the outer thread of where this function is called.
         * When called, the thread will be blocked until the server is unloaded.
         */
        SocketServerErrors block();

        /**
         * This function will return a boolean value indicating whether the
         * server is online or not.
         */
        [[nodiscard]] bool isOnline() const;

        /**
         * This function will return a boolean value indicating whether the
         * server is busy or not.
         */
        [[nodiscard]] bool isBusy() const;

        /**
         * Set the receive buffer size of the server.
         * @param size The size of the receive buffer.
         */
        SocketServerErrors setReceiveBufferSize(u16 size);

        /**
         * Set the trash collection interval time. This is the amount of time
         * to wait before cleaning useless threads and clients.
         * @param interval The interval time in milliseconds.
         */
        void setTrashCollectionInterval(u32 interval);

        /**
         * Emit and dispatch a message to all currently
         * connected sockets.
         * @param message The message to dispatch.
         */
        void emit(SocketServerAdapterMessage &message);

        /**
         * Emit and dispatch a string message to
         * all currently connected sockets.
         * @param message The message to dispatch.
         */
        void emit(const std::string &message);
    };
}
