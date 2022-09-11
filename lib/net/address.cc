#include "address.h"
#include "../string/deconstruction.h"
#include "../string/condition.h"
#include "../number/parsing.h"
#include <iostream>

namespace exolix {
    NetAddress::NetAddress(const std::string &hostname, exolix::u16 port) :
        port(port) {
        if (hostname == "::1") {
            this->hostname = "::1";

            version = InternetVersion::Ipv6;
            valid = true;

            return;
        } else if (hostname == "localhost") {
            this->hostname = "127.0.0.1";

            version = InternetVersion::Ipv4;
            valid = true;

            return;
        };

        auto partsIpv4 = StringDeconstruction::split(hostname, ".");
        auto partsIpv6 = StringDeconstruction::split(hostname, ":");

        if (partsIpv4.size() == 4 && !StringCondition::contains(hostname, ":")) {
            version = InternetVersion::Ipv4;

            for (auto &part : partsIpv4) {
                i64 partValue;
                auto errorParsed = NumberParsing::parseInteger(part, partValue);

                if (errorParsed != NumberParsingErrors::Ok) {
                    version = InternetVersion::Unknown;
                    valid = false;

                    break;
                }

                if (partValue < 0 || partValue > 255) {
                    version = InternetVersion::Unknown;
                    valid = false;

                    break;
                }

                valid = true;
            }
        } else if (partsIpv6.size() == 8 && !StringCondition::contains(hostname, ".")) {
            version = InternetVersion::Ipv6;

            i64 partValue;
            NumberParsingErrors errorParsed;

            for (auto &part : partsIpv6) {
                errorParsed = NumberParsing::parseHexInteger(part, partValue);

                if (errorParsed != NumberParsingErrors::Ok) {
                    version = InternetVersion::Unknown;
                    valid = false;

                    break;
                }

                if (partValue < 0 || partValue > 65535) {
                    version = InternetVersion::Unknown;
                    valid = false;

                    break;
                }

                valid = true;
            }
        } else {
            version = InternetVersion::Unknown;
            valid = false;
        }
    }

    NetAddressErrors NetAddress::getHostname(std::string &hostnameWritable) const {
        if (!valid)
            return NetAddressErrors::InvalidHostname;

        hostnameWritable = hostname;
        return NetAddressErrors::Ok;
    }

    NetAddressErrors NetAddress::getInternetVersion(exolix::InternetVersion &versionWritable) const {
        if (!valid)
            return NetAddressErrors::InvalidHostname;

        versionWritable = version;
        return NetAddressErrors::Ok;
    }

    bool NetAddress::isValid() const {
        return valid;
    }

    NetAddressErrors NetAddress::getPort(exolix::u16 &portWritable) const {
        return NetAddressErrors::Ok; // TODO: Implement
    }
}
