#include "address.hxx"

namespace exolix {
    NetAddress::NetAddress(uint16_t inputPort, std::string inputHost):
        port(inputPort), host(inputHost) {
    }

    NetAddress::NetAddress(uint16_t inputPort):
        port(inputPort) {
    }
}
