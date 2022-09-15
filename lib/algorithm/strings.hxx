#pragma once

#include <string>

namespace exolix {
    /**
     * Manipulate strings, replace contents, etc...
     */
    class AlgorithmStrings {
    public:
        /**
         * Check to see if a string starts with a certain
         * string.
         * @param str The string to check.
         * @return True if the string starts with the specified string.
         */
        static bool startsWith(const std::string &str, const std::string &start);

        /**
         * Check to see if a string ends with a certain
         * string.
         * @param str The string to check.
         * @return True if the string ends with the specified string.
         */
        static bool endsWith(const std::string &str, const std::string &end);

        /**
         * Transform string to all caps.
         * @param str The string to transform.
         * @return The transformed string.
         */
        static std::string toUpper(const std::string &str);

        /**
         * Transform string to all lowercase.
         * @param str The string to transform.
         * @return The transformed string.
         */
        static std::string toLower(const std::string &str);
    };
}
