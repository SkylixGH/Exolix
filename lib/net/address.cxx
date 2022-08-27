#include <utility>

#include "address.hxx"

namespace exolix {
    NetAddress::NetAddress(uint16_t inputPort, std::string inputHost):
        port(inputPort), host(std::move(inputHost)) {
    }

    NetAddress::NetAddress(uint16_t inputPort):
        port(inputPort) {
    }

    std::string NetAddress::getResolvedIp() {
        if (!isValidHost())
            throw NetAddressException(NetAddressErrors::INVALID_HOST, "The hostname is invalid");

        if (host == "localhost")
            return "0.0.0.0";

    }
}
