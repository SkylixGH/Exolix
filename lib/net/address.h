#pragma once

#include "../number/types.h"
#include <string>

namespace exolix {
    /**
     * This class is used for storing and manipulating IP addresses
     * and other network based addresses.
     */
    class NetAddress {
    private:
        /**
         * The hostname of the address.
         */
        const std::string hostname;

        /**
         * The port of the address.
         */
        const u16 port;

    public:
        /**
         * Create a new network address instance.
         * @param hostname The hostname to point to.
         * @param port The port to point to.
         */
        NetAddress(const std::string &hostname, u16 port);

        /**
         * Get the hostname of the address.
         * @return The hostname of the address.
         */
        const std::string &getHostname() const;

        /**
         * Get the port of the address.
         * @return The port of the address.
         */
        u16 getPort() const;
    };
}
