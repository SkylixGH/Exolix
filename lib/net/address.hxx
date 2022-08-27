#pragma once

#include <string>
#include <exolix.hxx>

#if defined(__linux__) || defined(__APPLE__)
#include <netinet/in.h>
#endif

namespace exolix {
    enum class NetAddressErrors {
        INVALID_HOST
    };

    typedef Error<NetAddressErrors> NetAddressException;

    class NetAddress {
    public:
        const uint16_t port;
        const std::string host;

        NetAddress(uint16_t inputPort, std::string inputHost);
        explicit NetAddress(uint16_t inputPort);

        bool isValidHost();
        bool hasPortPermission();

        std::string getResolvedIp();
    };
}
