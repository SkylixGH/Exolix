#include "parsing.hxx"

namespace exolix {
    __int128_t NumberParsing::parse(const std::string &string) {
        if (!NumberCondition::isNumber(string))
            throw NumberParsingException(
                NumberParsingErrors::INPUT_NOT_A_NUMBER,
                "The input provided to be parsed into an integer is "
                "not in the form of a real number and does not only "
                "consist of digits."
            );

        __int128_t number = 0;

        for (char c : string)
            number = number * 10 + (c - '0');

        return number;
    }
}
