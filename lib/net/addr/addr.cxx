#include <utility>
#include "addr.hxx"

#ifdef _WIN32
    #include <ws2tcpip.h>
#endif

namespace exolix {
    NetAddr::NetAddr(): port(0), host("::1"), version(NetVer::INET_6) {}

    NetAddr::NetAddr(uint16_t port): port(port), host("::1"), version(NetVer::INET_6) {}

    NetAddr::NetAddr(std::string hostname, uint16_t port):
        port(port), host(std::move(hostname)), version(NetVer::INET_6) {
    }

    bool NetAddr::isIpv4HostnameValid(const std::string &hostname) {
#ifdef _WIN32
        struct sockaddr_in sa {};
        int result = InetPtonA(AF_INET, hostname.c_str(), &(sa.sin_addr));
        return result != 0;
#endif

        // TODO: [Linux, Mac] Needs implementation
        throw std::runtime_error("Not implemented");
    }

    bool NetAddr::isIpv6HostnameValid(const std::string &hostname) {
#ifdef _WIN32
        struct sockaddr_in6 sa {};
        int result = InetPtonA(AF_INET6, hostname.c_str(), &(sa.sin6_addr));
        return result != 0;
#endif

        // TODO: [Linux, Mac] Needs implementation
        throw std::runtime_error("Not implemented");
    }

    Result<uint16_t, NetAddrErrors> NetAddr::findOpenPort() {
#ifdef _WIN32
        int result;

        SOCKET sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
        if (sock == INVALID_SOCKET)
            return Err(NetAddrErrors::UNKNOWN_ERROR_FINDING_PORT);

        sockaddr_in addr {};
        addr.sin_family = AF_INET;
        addr.sin_addr.s_addr = htonl(INADDR_ANY);
        addr.sin_port = 0;

        result = bind(sock, (SOCKADDR *) &addr, sizeof(addr));
        if (result == SOCKET_ERROR)
            return Err(NetAddrErrors::UNAVAILABLE_OS_PORTS);

        int addrLen = sizeof(addr);
        result = getsockname(sock, (SOCKADDR *) &addr, &addrLen);
        if (result == SOCKET_ERROR)
            return Err(NetAddrErrors::UNKNOWN_ERROR_FINDING_PORT);

        closesocket(sock);
        return Ok(ntohs(addr.sin_port));
#endif

        // TODO: [Linux, Mac] Needs implementation
        throw std::runtime_error("Not implemented");
    }
}
