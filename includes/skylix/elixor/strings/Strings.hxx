#pragma once

#include <vector>
#include <iostream>

using namespace std;

namespace Skylix::Elixor {
    class Strings {
    public:
        /**
         * @brief Split a string into multiple segments based on a delimeter.
         * 
         * @param full The full string to split.
         * @param delimeter The string split separator.
         * @return All the segments of the string, without the delimeter.
         */
        static vector<string> Split(string full, const string delimeter);

        /**
         * @brief Check to see if a string contains a specific string.
         * 
         * @param full The entire string to search.
         * @param query The string to use for searching.
         * @return If the query exists somewhere in the string.
         */
        static bool Contains(const string full, const string query);
    };
}