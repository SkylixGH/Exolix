#pragma once

#include <string>
#include <exolix.hxx>

namespace exolix {
    enum class NumberParsingErrors {
        INPUT_NOT_A_NUMBER,
        INPUT_TOO_LARGE
    };

    typedef Error<NumberParsingErrors> NumberParsingException;

    class NumberParsing {
    public:
        /**
         * Try to convert a string that is only a number into an actual integer.
         * @param string The input number in the form of a string.
         * @return The parsed number from the string.
         */
        static int64_t parse(const std::string &string);
    };
}
