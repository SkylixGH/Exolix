#include "token.hxx"
#include <algorithm>

namespace exolix {
    std::vector<std::string> StringTokenizer::split(const std::string &string, const std::string &delimiter) {
        std::vector<std::string> result;

        if (delimiter == "")
            for (char c : string)
                result.push_back(std::string(1, c));
        else {
            std::string current = "";

            for (char c : string) {
                if (c == delimiter[0]) {
                    result.push_back(current);
                    current = "";
                } else
                    current += c;
            }

            result.push_back(current);
        }

        return result;
    }

    std::string StringTokenizer::toString(__int128_t number) {
        std::string result = "";

        while (number > 0) {
            result += (char) (number % 10 + '0');
            number /= 10;
        }

        std::reverse(result.begin(), result.end());
        return result;
    }

    std::string StringTokenizer::toString(const std::string &string) {
        return string;
    }
}
