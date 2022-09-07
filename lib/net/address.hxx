#pragma once

#include <string>
#include "../error/error.hxx"

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
        explicit NetAddress(std::string inputHost);

        bool isValidHost();
        bool hasPortPermission();

        std::string getProcessed();

        static bool isValidIpv4Address(const std::string &inputIpv4);
    };
}