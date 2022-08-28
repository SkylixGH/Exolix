#include <utility>
#include "address.hxx"

#if defined(__linux__) || defined(__APPLE__)
#include <netdb.h>
#include <arpa/inet.h>
#elif _WIN32
#include <winsock2.h>
#endif

namespace exolix {
    NetAddress::NetAddress(uint16_t inputPort, std::string inputHost):
        port(inputPort), host(std::move(inputHost)) {
    }

    NetAddress::NetAddress(uint16_t inputPort):
        port(inputPort), host("localhost") {
    }

    NetAddress::NetAddress(std::string inputHost):
        port(0), host(inputHost) {
    }

    bool NetAddress::isValidHost() {
        return true;
    }

    std::string NetAddress::getProcessed() {
        if (!isValidHost())
            throw NetAddressException(NetAddressErrors::INVALID_HOST, "The hostname is invalid");

        std::string result;

        if (host == "localhost")
            result += "0.0.0.0";
        else
            result += host;

        if (port > 0)
            result += ":" + std::to_string(port);

        return result;
    }
}
