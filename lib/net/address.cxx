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
#include <unistd.h>

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

    u16 NetAddress::getAvailablePort() {
#if defined(__linux__) || defined(__APPLE__)
        int socketFd = socket(AF_INET, SOCK_STREAM, 0);

        if (socketFd < 0)
            return 0;

        sockaddr_in address{};
        address.sin_family = AF_INET;
        address.sin_addr.s_addr = INADDR_ANY;
        address.sin_port = 0;

        if (bind(socketFd, (sockaddr *) &address, sizeof(address)) < 0)
            return 0;

        socklen_t addressLength = sizeof(address);

        if (getsockname(socketFd, (sockaddr *) &address, &addressLength) < 0)
            return 0;

        close(socketFd);

        return ntohs(address.sin_port);
#elif defined(_WIN32)
        SOCKET socketFd = socket(AF_INET, SOCK_STREAM, 0);

        if (socketFd == INVALID_SOCKET)
            return 0;

        sockaddr_in address{};
        address.sin_family = AF_INET;
        address.sin_addr.s_addr = INADDR_ANY;
        address.sin_port = 0;

        if (bind(socketFd, (sockaddr *) &address, sizeof(address)) == SOCKET_ERROR)
            return 0;

        socklen_t addressLength = sizeof(address);

        if (getsockname(socketFd, (sockaddr *) &address, &addressLength) == SOCKET_ERROR)
            return 0;

        closesocket(socketFd);

        return ntohs(address.sin_port);
#endif
    }
}
