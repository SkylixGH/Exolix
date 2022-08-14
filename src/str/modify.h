#pragma once

#include <string>
#include <vector>

namespace exolix::str {
    class StringModify {
    public:
        static std::string toUpper(const std::string &str);

        static std::vector<std::string> split(const std::string &str, const std::string &delim);
    };
}
