/*
 * This class handles the addresses, basically converting them from string to ipv4.
 */

#include <utility>
#include <vector>
#include "address.hxx"
#include "../process/process.hxx"
#include "../number/condition.hxx"
#include "../number/parsing.hxx"
#include "../string/token.hxx"

namespace exolix {
    NetAddress::NetAddress(u16 inputPort, std::string inputHost) :
            port(inputPort), host(std::move(inputHost)) {
    }

    NetAddress::NetAddress(u16 inputPort) :
            port(inputPort), host("127.0.0.1") {
    }

    NetAddress::NetAddress(std::string inputHost) :
            port(0), host(std::move(inputHost)) {
    }

    bool NetAddress::isValidHost() {
        return NetAddress::isValidIpv4Address(getProcessed());
    }

    std::string NetAddress::getProcessed() {
        std::string result;

        if (host == "localhost")
            result += "127.0.0.1";
        else
            result += host;

        return result;
    }

    bool NetAddress::isValidIpv4Address(const std::string &inputIpv4) {
        if (inputIpv4.length() == 0)
            return false;

        std::vector<std::string> parts = StringTokenizer::split(inputIpv4, ".");

        if (parts.size() != 4)
            return false;

        for (const std::string &part: parts) {
            if (!NumberCondition::isNumber(part))
                return false;

            int64_t number;

            try {
                number = NumberParsing::parse(part);
            } catch (...) {
                return false;
            };

            if (number > 255 || number < 0)
                return false;
        }

        return true;
    }

    bool NetAddress::hasPortPermission() const {
        if (port < 1024)
            return Process::hasRoot();
        else if (port > 65535)
            return false;

        return true;
    }
}
