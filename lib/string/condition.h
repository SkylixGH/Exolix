#pragma once

#include <string>

namespace exolix {
    /**
     * A class used for doing conditionals on strings.
     */
    class StringCondition {
    public:
        /**
         * Check to see if a string contains a value.
         * @param source The input string that should be checked.
         * @param value The value that should be checked for.
         * @return If the string contains the value.
         */
        static bool contains(const std::string &source, const std::string &value);

        /**
         * Check to see if a string contains a number.
         * @param source The input string that should be checked.
         * @return If the string contains a number.
         */
        static bool containsNumber(const std::string &source);
    };
}
