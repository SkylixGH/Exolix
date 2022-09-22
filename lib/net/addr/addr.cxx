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
}
