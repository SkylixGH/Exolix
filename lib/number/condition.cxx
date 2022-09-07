#include "condition.hxx"
#include "../string/condition.hxx"

namespace exolix {
    bool NumberCondition::isEven(u64 number) {
        return number % 2 == 0;
    }

    bool NumberCondition::isOdd(u64 number) {
        return number % 2 != 0;
    }

    bool NumberCondition::isNumber(u64 number) {
        return true;
    }

    bool NumberCondition::isNumber(const std::string &string) {
        std::string toParse = string;

        if (StringCondition::startsWith(string, "-")) {
            toParse = string.substr(1);
        }

        if (StringCondition::startsWith(toParse, "-")) {
            return false;
        }

        for (char c : toParse)
            if (!isdigit(c))
                return false;

        return true;
    }
}