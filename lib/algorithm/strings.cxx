#include <execution>
#include "strings.hxx"

namespace exolix {
    bool AlgorithmStrings::startsWith(const std::string &str, const std::string &start) {
        if (str.length() < start.length()) {
            return false;
        }

        return str.compare(0, start.length(), start) == 0;
    }

    bool AlgorithmStrings::endsWith(const std::string &str, const std::string &end) {
        if (str.length() < end.length()) {
            return false;
        }

        return str.compare(str.length() - end.length(), end.length(), end) == 0;
    }

    std::string AlgorithmStrings::toUpper(const std::string &str) {
        std::string newStr = str;
        std::transform(newStr.begin(), newStr.end(), newStr.begin(), ::toupper);
        return newStr;
    }

    std::string AlgorithmStrings::toLower(const std::string &str) {
        std::string newStr = str;
        std::transform(newStr.begin(), newStr.end(), newStr.begin(), ::tolower);
        return newStr;
    }
}
