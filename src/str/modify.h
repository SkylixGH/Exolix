#pragma once

#include <string>
#include <vector>

namespace exolix::str {
    class StringModify {
    public:
        static std::string trim(const std::string &str);

        static std::string trim_left(const std::string &str);

        static std::string trim_right(const std::string &str);

        static std::vector<std::string> split(const std::string &str, const std::string &delim);
    };
}
