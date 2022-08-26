#pragma once

#include <string>
#include <exolix.hxx>

#if defined(__linux__) || defined(__APPLE__)
#include <netinet/in.h>
#endif

namespace exolix {
    class NetAddress {
    public:
        const uint16_t port;
        const std::string host;

        NetAddress(uint16_t inputPort, std::string inputHost);
        NetAddress(uint16_t inputPort);

        bool isValidHost();
        bool isValidPort();

        bool hasPortPermission();
    };
}
