#pragma once

#include <iostream>
#include <optional>

namespace exolix {
    template <typename ErrorType>
    class Err {
    public:
        const ErrorType error;

        explicit Err(ErrorType error) : error(error) {};
    };

    template <typename ValueType>
    class Ok {
    public:
        const ValueType value;

        explicit Ok(ValueType value) : value(value) {};
    };

    template <typename ValueType, typename ErrorType>
    class Result {
    private:
//
//        std::optional<ValueType> value;
//        std::optional<ErrorType> error;

        union {
            ValueType value;
            ErrorType error;
        };

    public:
        const bool isError;

        Result(Ok<ValueType> value) : value(value.value), isError(false) {}

        Result(Err<ErrorType> error) : error(error.error), isError(true) {}

        ~Result() {
            if (isError) {
                error.~ErrorType();
            } else {
                value.~ValueType();
            }
        }

        ValueType get() {
            if (isError) {
                throw std::runtime_error("Result has error");
            }

            return value;
        }

        ErrorType getError() {
            if (!isError)
                throw std::runtime_error("Result has no error");

            return error;
        }
    };
}
