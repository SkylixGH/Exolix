#pragma once

#include <string>

namespace exolix {
    /**
     * This class is used for converting strings into different formats.
     */
    class StringConversion {
    public:
        /**
         * Convert a string into a lower case string.
         * @param source The input string that should be converted.
         * @return The lower case string.
         */
        static std::string toLowerCase(std::string source);
    };
}
