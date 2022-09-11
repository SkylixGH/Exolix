#pragma once

#include <string>
#include <vector>

namespace exolix {
    /**
     * This class is used for deconstructing string like
     * separating them into multiple pieces and more.
     */
    class StringDeconstruction {
    public:
        /**
         * Split a string into multiple slices.
         * @param source The input string that should be split.
         * @param delimiter The delimiter that should be used for splitting, these will not be in the output.
         * @return A vector of strings that are the result of the split.
         */
        static std::vector<std::string> split(const std::string &source, const std::string &delimiter);
    };
}
