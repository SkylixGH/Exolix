#pragma once

#include "./types.h"
#include <string>

namespace exolix {
    /**
     * A list of error codes or success codes that could
     * be returned by members of the number parser.
     */
    enum class NumberParsingErrors {
        /**
         * No errors occurred. The action has completed
         * successfully.
         */
        Ok,

        /**
         * The number was not a valid number and could not
         * be parsed.
         */
        InvalidNumber,

        /**
         * The number was too large or too small to be
         * parsed.
         */
        NumberOutOfRange,

        /**
         * The number was not a valid hex integer and could not
         * be parsed.
         */
        InvalidHexInteger,
    };

    /**
     * A class used for parsing numbers from strings.
     */
    class NumberParsing {
    public:
        /**
         * Parse a number from a string into
         * and integer and write to a result.
         * @param source The input string that should be parsed.
         * @param result The result that should be written to.
         * @return If the parsing was successful or its error.
         */
        static NumberParsingErrors parseInteger(std::string source, i64 &result);

        /**
         * Parse an to an integer from a hex string.
         * @param source The input string that should be parsed.
         * @param result The result that should be written to.
         * @return If the parsing was successful or its error.
         */
        static NumberParsingErrors parseHexInteger(std::string source, i64 &result);
    };
}
