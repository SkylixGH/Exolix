#pragma once

#include <cstdint>
#include <string>

namespace exolix {
    class NumberCondition {
    public:
        static bool isEven(__int128_t number);
        static bool isOdd(__int128_t number);

        static bool isNumber(__int128_t number);
        static bool isNumber(const std::string &string);
    };
}
