#pragma once

#include <string>
#include "../error/error.hxx"
#include "../types.hxx"

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
        const u16 port;
        const std::string host;

        NetAddress(u16 inputPort, std::string inputHost);

        explicit NetAddress(u16 inputPort);

        explicit NetAddress(std::string inputHost);

        bool isValidHost();

        bool hasPortPermission() const;

        std::string getProcessed();

        static bool isValidIpv4Address(const std::string &inputIpv4);

        static u16 getAvailablePort();
    };
}
