#include "condition.hxx"

namespace exolix {
    bool StringCondition::contains(const std::string &string, const std::string &search) {
        return string.find(search) != std::string::npos;
    }

    bool StringCondition::startsWith(const std::string &string, const std::string &search) {
        return string.find(search) == 0;
    }
}
