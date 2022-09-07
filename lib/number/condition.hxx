#pragma once

#include <cstdint>
#include <string>

namespace exolix {
    class NumberCondition {
    public:
        static bool isEven(uint64_t number);
        static bool isOdd(uint64_t number);

        static bool isNumber(uint64_t number);
        static bool isNumber(const std::string &string);
    };
}
