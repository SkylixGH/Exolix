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

    public:
        /**
         * Create a new network address instance.
         * @param hostname The hostname to point to.
         * @param port The port to point to.
         */
        NetAddress(const std::string &hostname, u16 port);

        ~NetAddress();
    };
}
