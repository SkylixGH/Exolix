#pragma once

#include <cstdint>
#include <string>
#include "../types.hxx"

namespace exolix {
    class NumberCondition {
    public:
        static bool isEven(u64 number);
        static bool isOdd(u64 number);

        static bool isNumber(u64 number);
        static bool isNumber(const std::string &string);
    };
}
