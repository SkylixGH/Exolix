#include "tcpip.h"
#include <utility>

#if defined(__linux__) || defined(__APPLE__)

    #include <netdb.h>
    #include <netinet/in.h>
    #include <unistd.h>
    #include <strings.h>
    #include <cstring>

#endif

namespace exolix {
    SocketServer::SocketServer(exolix::NetAddress address, int backlog) :
            address(std::move(address)), backlog(backlog) {
    }

    SocketServer::~SocketServer() {
        if (serverThread != nullptr)
            serverThread->block();

        delete serverThread;
    }

    SocketServerErrors SocketServer::setTLS(bool enabled) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        tls = enabled;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::setCertificate(const std::string &path) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        if (!tls)
            return SocketServerErrors::TlsNotEnabled;

        certificate = path;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::setPrivateKey(const std::string &path) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        if (!tls)
            return SocketServerErrors::TlsNotEnabled;

        privateKey = path;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::load() {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        busy = true;

#if false
        int socketFd;
        int extSocketFd;
        int clientLength;

        int bytesReceived;

        std::string hostname;
        u16 portNumber;

        struct sockaddr_in serverAddress {};
        struct sockaddr_in clientAddress {};

        char *buffer = new char[receiveBufferSize];

        socketFd = socket(AF_INET, SOCK_STREAM, 0);
        if (socketFd < 0) {
            // TODO: Handle
        }

        bzero((char *) &serverAddress, sizeof(serverAddress));

        int versionFamily;

        switch (ipVersion) {
            case InternetVersion::Ipv4:
                versionFamily = AF_INET;
                break;

            case InternetVersion::Ipv6:
                versionFamily = AF_INET6;
                break;
        }

        struct hostent *hostEntry {};

        auto addressGetHostnameError = address.getHostname(hostname);
        if (addressGetHostnameError != NetAddressErrors::Ok) {
            // TODO: Handle
        }

        const auto hostNameCString = hostname.c_str();

        hostEntry = gethostbyname(hostNameCString);
        if (hostEntry == nullptr) {
            // TODO: Handle
        }

        memcpy(&serverAddress.sin_addr, hostEntry->h_addr_list[0], hostEntry->h_length);

        auto addressGetPortError = address.getPort(portNumber);
        if (addressGetPortError != NetAddressErrors::Ok) {
            // TODO: Hande
        }

        serverAddress.sin_family = versionFamily;
        serverAddress.sin_addr.s_addr = INADDR_ANY;

        delete [] buffer; // TODO: Move to thread

        return SocketServerErrors::Ok; // TODO: Implement correctly
#endif

        busy = false;
        return SocketServerErrors::Ok;
    }

    SocketServerErrors SocketServer::unload() {
        return SocketServerErrors::Ok; // TODO: Implement correctly
    }

    bool SocketServer::isOnline() const {
        return online;
    }

    bool SocketServer::isBusy() const {
        return busy;
    }

    SocketServerErrors SocketServer::block() {
        if (serverThread == nullptr || !serverThread->isActive())
            return SocketServerErrors::ServerNotReadyForAction;

        auto blockRes = serverThread->block();

        switch (blockRes) {
            case ThreadErrors::ThreadNotActive:
                return SocketServerErrors::ServerNotReadyForAction;

            case ThreadErrors::ThreadAlreadyBlocked:
                return SocketServerErrors::CannotBlockServerAfterPreviouslyBlockedWithoutRestart;

            case ThreadErrors::Ok:
                return SocketServerErrors::Ok;
        }
    }

    SocketServerErrors SocketServer::setReceiveBufferSize(u16 size) {
        if (online || busy)
            return SocketServerErrors::ServerDangerousActionWhileOnline;

        receiveBufferSize = size;
        return SocketServerErrors::Ok;
    }
}
