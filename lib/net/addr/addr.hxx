#pragma once

#include <string>
#include "../../result/result.hxx"

namespace exolix {
    /**
     * Network protocol versions.
     */
    enum class NetVer {
        /**
         * Internet IP protocol version standard named
         * IPv4
         */
        INET_4,

        /**
         * Internet IP protocol version standard named
         * IPv6
         */
        INET_6
    };

    /**
     * A classed used for storing a network address. The address
     * supports multiple IP versions.
     */
    class NetAddr {
    private:
        /**
         * The IP version.
         */
        NetVer version;

        /**
         * The address hostname.
         */
        std::string host;

        /**
         * The address port.
         */
        uint16_t port;

    public:
        /**
         * Create a new net address with a defaulted
         * hostname and port.
         */
        NetAddr();

        /**
         * Create a new net address with a custom
         * port.
         * @param port The port to use for the address.
         */
        explicit NetAddr(uint16_t port);

        /**
         * Create a new net address with a custom
         * hostname and port.
         * @param hostname The hostname to use for the address.
         * @param port The port to use for the address.
         */
        NetAddr(std::string hostname, uint16_t port);

        /**
         * Check to see if a given IPv4 network hostname is valid
         * and can be parsed.
         * @param hostname The hostname to validate.
         */
        static bool isIpv4HostnameValid(const std::string &hostname);

        /**
         * Check to see if a given IPv6 network hostname is valid
         * and can be parsed.
         * @param hostname The hostname to validate.
         */
        static bool isIpv6HostnameValid(const std::string &hostname);
    };
}
