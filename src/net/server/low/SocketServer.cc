#include <exolix.h>
#include <utility>
#include <thread>
#ifdef __linux__
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <skylix.h>
#endif

using skylix::Error;

namespace exolix::net::server::low {
    SocketServer::SocketServer(Type type, std::string host, uint16_t port, uint16_t backlog) {
        this->host = std::move(host);
        this->port = port;
        this->type = type;
        this->backlog = backlog;
    }

    void SocketServer::setOnAccept(std::function<void(int)> onAcceptFn) {
        this->onAccept = std::move(onAcceptFn);
    }

    void SocketServer::setOnMessage(std::function<void(int, std::string)> onMessageFn) {
        this->onMessage = std::move(onMessageFn);
    }

    void SocketServer::setOnClose(std::function<void(int)> onCloseFn) {
        this->onClose = std::move(onCloseFn);
    }

    void SocketServer::bind() {
        if (online) return;
        online = true;

#ifdef __linux__
        struct sockaddr_in address;
        int addressLength = sizeof(address);
        int opt = 1;

        this->serverHandle = socket(AF_INET, this->type == Type::TCP ? SOCK_STREAM : SOCK_DGRAM, 0);
        if (this->serverHandle == 0) {
            throw Error(BindErrors::COULD_NOT_CREATE_SOCKET, "Failed to create the socket");
        }

        if (setsockopt(serverHandle, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
            throw Error(BindErrors::COULD_NOT_SET_OPTIONS, "Failed to set the socket options");
        }

        address.sin_family = AF_INET;
        address.sin_addr.s_addr = INADDR_ANY;
        address.sin_port = htons(this->port);

        if (::bind(this->serverHandle, (struct sockaddr*) &address, sizeof(address)) < 0) {
            throw Error(BindErrors::COULD_NOT_BIND_SOCKET, "Failed to bind the socket");
        }

        if (listen(this->serverHandle, this->backlog) < 0) {
            throw Error(BindErrors::COULD_NOT_LISTEN_SOCKET, "Failed to listen on the socket");
        }

        while (online) {
            int newSocket = accept(this->serverHandle, (struct sockaddr*) &address, (socklen_t*) &addressLength);

            std::thread([this, newSocket] {
                if (this->onAccept)
                    this->onAccept(newSocket);

                bool socketOpen = true;
                while (socketOpen) {
                    char buffer[1024] = { 0 };
                    int valread = read(newSocket, buffer, 1024);

                    if (valread == 0) {
                        socketOpen = false;

                        if (this->onClose)
                            this->onClose(newSocket);
                    } else {
                        if (this->onMessage)
                            this->onMessage(newSocket, std::string(buffer));
                    }
                }
            }).detach();
        }
#endif
    }
}
