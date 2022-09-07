#include "parsing.hxx"
#include "../string/condition.hxx"
#include "condition.hxx"
#include <string>

namespace exolix {
    i64 NumberParsing::parse(const std::string &string) {
        const int max64bitNumberLength = 19;
        const u64 max64bitNumber = 9223372036854775807;

        i64 number = 0;
        bool negative = false;
        
        if (!NumberCondition::isNumber(string))
            throw NumberParsingException(NumberParsingErrors::INPUT_NOT_A_NUMBER, "The number provided is not a number, please ensure that the number only contains digits and possibly a negative sign at the start.");

        if (StringCondition::startsWith(string, "-"))
            negative = true;

        if (
            (string.length() > max64bitNumberLength && !negative ||
            (string.length() > max64bitNumberLength + 1 && negative))
        )
            throw NumberParsingException(NumberParsingErrors::INPUT_TOO_LARGE, "The input number is too large or small to be parsed, number must be 64 bits signed or unsigned");

        std::string processedString = string;

        if (negative) processedString = processedString.substr(1);

        for (char character : processedString) {
            if (character == '-')
                continue;

            number *= 10;
            number += character - '0';
        }

        if (number > max64bitNumber)
            throw NumberParsingException(NumberParsingErrors::INPUT_TOO_LARGE, "The input number is too large to be parsed, number must be 64 bits signed or unsigned");

        if (negative) number *= -1;

        return number;
    }
}
