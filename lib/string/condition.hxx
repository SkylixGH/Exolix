#pragma once

#include <string>

namespace exolix {
    class StringCondition {
    public:
        static bool contains(const std::string &string, const std::string &search);
        static bool startsWith(const std::string &string, const std::string &search);
    };
}
