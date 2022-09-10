#include "address.h"

namespace exolix {
    NetAddress::NetAddress(const std::string &hostname, exolix::u16 port):
        hostname(hostname == "localhost" ? "127.0.0.1" : hostname), port(port) {
    }

    const std::string &NetAddress::getHostname() const {
        return hostname;
    }

    exolix::u16 NetAddress::getPort() const {
        return port;
    }
}
