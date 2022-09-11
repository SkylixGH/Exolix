#include "condition.h"

namespace exolix {
    bool StringCondition::contains(const std::string &source, const std::string &value) {
        return source.find(value) != std::string::npos;
    }

    bool StringCondition::containsNumber(const std::string &source) {
        return contains(source, "0") || contains(source, "1") || contains(source, "2") || contains(source, "3") ||
               contains(source, "4") || contains(source, "5") || contains(source, "6") || contains(source, "7") ||
               contains(source, "8") || contains(source, "9");
    }

    bool StringCondition::startsWith(const std::string &source, const std::string &value) {
        return source.find(value) == 0;
    }
}
