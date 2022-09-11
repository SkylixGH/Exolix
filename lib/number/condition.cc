#include "condition.h"
#include "../string/condition.h"

namespace exolix {
    bool NumberCondition::isInteger(std::string source) {
        if (source.empty()) {
            return false;
        }

        if (StringCondition::contains(source, ".")) {
            return false;
        }

        if (StringCondition::contains(source, "-")) {
            source = source.substr(1);
        }

        if

        for (int i = 0; i < source.size(); i++) {
            if (!std::isdigit(source[i])) {
                return false;
            }
        }

        return true;
    }
}
