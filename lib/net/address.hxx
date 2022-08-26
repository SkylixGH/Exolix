#pragma once

#include <string>

namespace exolix {
    class NetAddress {
    public:
        NetAddress(uint16_t port, std::string host);
        NetAddress(uint16_t port);
    };
}
