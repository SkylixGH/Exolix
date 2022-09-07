#pragma once

#include <vector>
#include <string>

namespace exolix {
    class StringTokenizer {
    public:
        static std::vector<std::string> split(const std::string &string, const std::string &delimiter);

        static std::string toString(__int128_t number);
        static std::string toString(const std::string &string);
    };
}
