#include <utility>
#include "addr.hxx"

#ifdef _WIN32
    #include <ws2tcpip.h>
#endif

namespace exolix {
    std::string exolix_NetAddr_strerror(NetAddrErrors error) {
        switch (error) {
            case NetAddrErrors::UNKNOWN_ERROR_FINDING_PORT:
                return "Unknown error finding port";

            case NetAddrErrors::UNAVAILABLE_OS_PORTS:
                return "OS could not find a port that was available for listening";

            case NetAddrErrors::INVALID_HOST_SOURCE:
                return "The hostname is deformed for the address and the OS deemed it unusable, invalid, or as a bad format";
        }
    }

    NetAddr::NetAddr(): port(0), host("::1"), version(NetVer::INET_6) {}

    NetAddr::NetAddr(uint16_t port): port(port), host("::1"), version(NetVer::INET_6) {}

    NetAddr::NetAddr(std::string hostname): port(0), host(std::move(hostname)), version(NetVer::INET_6) {}

    NetAddr::NetAddr(std::string hostname, uint16_t port):
        port(port), host(std::move(hostname)), version(NetVer::INET_6) {
    }

    Result<uint16_t, NetAddrErrors> NetAddr::getPort() {
        if (port == 0) {
            auto pRes = NetAddr::findOpenPort();
            if (pRes.isError) return pRes;

            port = pRes.get();
        }

        return Ok(port);
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

    Result<std::string, NetAddrErrors> NetAddr::getHost() {
        const bool validIpv4 = NetAddr::isIpv4HostnameValid(host);
        const bool validIpv6 = NetAddr::isIpv6HostnameValid(host);

        if (validIpv4) {
            version = NetVer::INET_4;
        } else if (validIpv6) {
            version = NetVer::INET_6;
        } else {
            return Err(NetAddrErrors::INVALID_HOST_SOURCE);
        }

        return Ok(host);
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
