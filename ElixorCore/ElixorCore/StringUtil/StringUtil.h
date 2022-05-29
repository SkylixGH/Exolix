#pragma once

#include <string>
#include <vector>

using namespace std;

namespace ElixorFramework::ElixorCore {
    class StringUtil {
    public:
        /**
         * @brief Split a string into multiple segments by a delimiter.
         * 
         * @param line The entire string to split.
         * @param delimiter The separator to split the string by.
         * @return The string as a vector of segments.
         */
        static vector<string> Split(string line, string delimiter);

        /**
         * @brief Check to see if a string contains a certain substring.
         * 
         * @param line The entire string to check.
         * @param substring The substring to check for.
         */
        static bool Contains(string line, string substring);
    };
}
