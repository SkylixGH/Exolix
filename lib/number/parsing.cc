#include "parsing.h"
#include "../string/condition.h"
#include "../number/condition.h"

namespace exolix {
    NumberParsingErrors NumberParsing::parseInteger(std::string source, i64 &result) {
        if (!NumberCondition::isInteger(source)) {
            return NumberParsingErrors::InvalidNumber;
        }

        bool negative = false;
        if (StringCondition::contains(source, "-")) {
            negative = true;
            source = source.substr(1);
        }

        const i8 maxDigits = 19;

        if (source.size() > maxDigits || source.empty()) {
            return NumberParsingErrors::NumberOutOfRange;
        }

        i64 parsed;

        try {
            parsed = std::stoll(source);
        } catch (...) {
            return NumberParsingErrors::InvalidNumber;
        }

        result = negative ? -parsed : parsed;
        return NumberParsingErrors::Ok;
    }

    NumberParsingErrors NumberParsing::parseHexInteger(std::string source, i64 &result) {
        if (!NumberCondition::isHexInteger(source)) {
            return NumberParsingErrors::InvalidHexInteger;
        }

        bool negative = false;
        if (StringCondition::contains(source, "-")) {
            negative = true;
            source = source.substr(1);
        }

        const i8 maxDigits = 16;

        if (source.size() > maxDigits || source.empty()) {
            return NumberParsingErrors::NumberOutOfRange;
        }

        i64 parsed;

        try {
            parsed = std::stoll(source, nullptr, 16);
        } catch (...) {
            return NumberParsingErrors::InvalidNumber;
        }

        result = negative ? -parsed : parsed;
        return NumberParsingErrors::Ok;
    }
}
