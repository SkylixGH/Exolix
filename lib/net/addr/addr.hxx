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
     * An enumerated list of possible error codes that can be sent
     * from members of a network address object.
     */
    enum class NetAddrErrors {
        /**
         * Unavailable ports for socket server listening.
         */
        UNAVAILABLE_OS_PORTS,

        /**
         * The address object failed to find an open port
         * for listening, this is usually due to the OS not supporting
         * networking or the OS being corrupted. This reason is for unknown
         * reasons, but if the OS ran out of ports, this error would not be
         * thrown but a different error would be thrown.
         */
        UNKNOWN_ERROR_FINDING_PORT,

        /**
         * The hostname is deformed for the address and the OS deemed
         * it unusable, invalid, or as a bad format.
         */
        INVALID_HOST_SOURCE
    };

    /**
     * Convert a net address error to a stringed error message.
     * @param error The error to convert.
     * @return The stringed error message.
     */
    std::string exolix_NetAddr_strerror(NetAddrErrors error);

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
         * Create a new net address with a custom hostname.
         * @param hostname The hostname to use for the address.
         */
        explicit NetAddr(std::string hostname);

        /**
         * Get the address port. If the port was initialized as no
         * parameter or 0, then the port will be auto selected.
         */
        Result<uint16_t, NetAddrErrors> getPort();

        /**
         * Get the address hostname. If the hostname is invalid, this
         * is where you can find out.
         * @return Result object containing the hostname, otherwise an error.
         */
        Result<std::string, NetAddrErrors> getHost();

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

        /**
         * Find an open port on the local machine.
         * @return A result object that may contain the port otherwise
         * the error information.
         */
        static Result<uint16_t, NetAddrErrors> findOpenPort();
    };
}
