/*
 * This class handles the addresses, basically converting them from string to ipv4.
 */

#include <utility>
#include "address.hxx"
#include <exolix.hxx>

//temp
#include <iostream>

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
        if (host.length() == 0)
            return false;

        // TODO: Add more validation layers
        return true;
    }

    std::string NetAddress::getProcessed() {
        if (!isValidHost())
            throw NetAddressException(NetAddressErrors::INVALID_HOST, "The hostname is invalid");

        std::string result;

        if (host == "localhost")
            result += "127.0.0.1";
        else
            result += host;

        if (port > 0)
            result += ":" + std::to_string(port);

        return result;
    }

    bool NetAddress::isValidIpv4Address(const std::string &inputIpv4) {
        std::vector<std::string> parts = StringTokenizer::split(inputIpv4, ".");

        if (parts.size() != 4)
            return false;

        for (std::string part : parts) {
            if (!NumberCondition::isNumber(part))
                return false;

            __int128_t number = NumberParsing::parse(part);

            if (number > 255 || number < 0)
                return false;
        }

        return true;
    }
}
