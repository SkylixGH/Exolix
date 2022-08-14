#include "modify.h"
#include <algorithm>

namespace exolix::str {
    std::string StringModify::toUpper(const std::string &str) {
        std::string result = str;
        std::transform(result.begin(), result.end(), result.begin(), toupper);

        return result;
    }

    std::vector<std::string> StringModify::split(const std::string &str, const std::string &delim) {
        if (delim.empty()) {
            std::vector<std::string> result;

            for (auto& c : str) {
                result.emplace_back(1, c);
            }

            return result;
        }

        size_t positionStart = 0;
        size_t delimLength = delim.length();
        size_t positionEnd;

        std::string token;
        std::vector<std::string> result {};

        while ((positionEnd = str.find(delim, positionStart)) != std::string::npos) {
            token = str.substr(positionStart, positionEnd - positionStart);
            positionStart = positionEnd + delimLength;

            result.push_back(token);
        }

        result.push_back(str.substr(positionStart));
        return result;
    }
}
