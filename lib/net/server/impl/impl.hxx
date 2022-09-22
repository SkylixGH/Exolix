#pragma once

#include "../../../result/result.hxx"

namespace exolix {
    /**
     * An enumerated list of errors that can be thrown by a socket server
     * implemented with SocketServerImpl class.
     */
    enum class SocketServerImplErrors {
        /**
         * The server was requested to be started but it was already online.
         */
        ONLINE_USELESS_CALL,

        /**
         * The server was requested to be stopped but it was already offline.
         */
        OFFLINE_USELESS_CALL,

        /**
         * The server was requested to do a job but the server was busy and cannot
         * perform the requested action.
         */
        CALLED_WHILE_BUSY
    };

    /**
     * A class that is abstract and enforces a structure that allows
     * it to extend socket server capabilities for other platforms.
     */
    class SocketServerImpl {
    protected:
        /**
         * The server listen implementation. This is called when the server
         * is safe to start.
         */
        virtual void listen() = 0;

    public:
        /**
         * Start the implementation server. This will open the server to
         * a specific address.
         * @return A result type containing nullptr if there is no error,
         * otherwise an error will be embedded into it.
         */
        Result<nullptr_t, SocketServerImplErrors> start();

        /**
         * Stop the implementation server. This will attempt to close
         * the server.
         * @return A result type containing nullptr if there is no error,
         */
        Result<nullptr_t, SocketServerImplErrors> stop();
    };
}
