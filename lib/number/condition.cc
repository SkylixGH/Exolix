#include "condition.h"
#include "../string/condition.h"
#include "../string/conversion.h"

namespace exolix {
    bool NumberCondition::isInteger(std::string source) {
        if (source.empty()) {
            return false;
        }

        if (StringCondition::contains(source, ".")) {
            return false;
        }

        if (StringCondition::startsWith(source, "-")) {
            source = source.substr(1);
        }

        if (StringCondition::startsWith(source, "-")) {
            return false;
        }

        for (int i = 0; i < source.size(); i++) {
            if (!std::isdigit(source[i])) {
                return false;
            }
        }

        return true;
    }

    bool NumberCondition::isHexInteger(std::string source) {
        if (source.empty()) {
            return false;
        }

        if (StringCondition::contains(source, ".")) {
            return false;
        }

        if (StringCondition::startsWith(source, "-")) {
            source = source.substr(1);
        }

        if (StringCondition::startsWith(source, "-")) {
            return false;
        }

        source = StringConversion::toLowerCase(source);

        const char hexChars[6] = {'a', 'b', 'c', 'd', 'e', 'f'};
        const char hexNumbers[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (auto symbol : source) {
            bool valid = false;

            for (auto hexChar : hexChars) {
                if (symbol == hexChar) {
                    valid = true;
                }
            }

            if (!valid) {
                for (auto hexNumber : hexNumbers) {
                    if (symbol == hexNumber) {
                        valid = true;
                    }
                }
            }

            if (!valid) {
                return false;
            }
        }

        return true;
    }
}
