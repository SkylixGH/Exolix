#pragma once

#include "../number/types.h"
#include <string>

namespace exolix {
    /**
     * An enumerated list of supported internet family
     * versions supported by Exolix
     */
    enum class InternetVersion {
        /**
         * Internet family version Ipv4, this format follows
         * like this: x.x.x.x where x can range form 0 to 255.
         */
        Ipv4,

        /**
         * Internet family version Ipv6, this format follows
         * like this: x:x:x:x:x:x:x:x where x can range form 0 to 65535.
         */
        Ipv6,

        /**
         * Unknown internet family version.
         */
        Unknown
    };

    /**
     * An enumerated list of possible errors and success codes
     * that can be returned by members of a net address instance.
     */
    enum class NetAddressErrors {
        /**
         * No errors occurred. The action has completed
         * successfully.
         */
         Ok,

         /**
          * The port was invalid and could not be applied
          * and will not be set to the return port.
          */
         InvalidPort,

         /**
          * The hostname was invalid and could not be applied
          */
        InvalidHostname,
    };

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

        /**
         * The internet version of the address.
         */
        InternetVersion version;

        /**
         * If the address is valid or not.
         */
        bool valid;

    public:
        /**
         * Create a new network address instance.
         * @param hostname The hostname to point to.
         * @param port The port to point to.
         */
        NetAddress(const std::string &hostname, u16 port);

        /**
         * Get the hostname of the address.
         * @param hostname The hostname to write to.
         * @return An error or success code.
         */
        NetAddressErrors getHostname(std::string &hostnameWritable) const;

        /**
         * Get the port of the address.
         * @param port The port to write to.
         * @return An error or success code.
         */
        NetAddressErrors getPort(u16 &portWritable) const;

        /**
         * Get the internet version of the address.
         * @param version The version to write to.
         * @return An error or success code.
         */
        NetAddressErrors getInternetVersion(InternetVersion &versionWritable) const;

        /**
         * Check if the address is valid.
         * @return If the address is valid.
         */
        bool isValid() const;
    };
}
