#pragma once

#include <iostream>

// TODO: Add unhandled exception listener

namespace exolix {
    /**
     * An error datatype return compatible with the result
     * function return type.
     * @tparam ErrorType The datatype used to store the error code.
     */
    template<typename ErrorType>
    class Err {
    public:
        /**
         * The error code.
         */
        const ErrorType error;

        /**
         * Create a new error instance.
         * @param error The error code or data.
         */
        explicit Err(ErrorType error) : error(error) {};
    };

    /**
     * An ok datatype return compatible with the result
     * @tparam ValueType The datatype used to store the value. Use nullptr_t if no value is needed.
     */
    template<typename ValueType>
    class Ok {
    public:
        /**
         * The value.
         */
        const ValueType value;

        /**
         * Create a new Ok instance.
         * @param value The value.
         */
        explicit Ok(ValueType value) : value(value) {};
    };

    /**
     * The result datatype for function returns.
     * @tparam ValueType The datatype used to store the value. Use nullptr_t if no value is returned.
     * @tparam ErrorType The datatype used to store the error code.
     */
    template<typename ValueType, typename ErrorType>
    class Result {
    private:
        union {
            /**
             * The value data.
             */
            ValueType value;

            /**
             * The error data.
             */
            ErrorType error;
        };

    public:
        /**
         * Whether the result is an error or not.
         */
        const bool isError;

        /**
         * Create a new return value with no error.
         * @param value The value.
         */
        // NOLINTNEXTLINE(google-explicit-constructor)
        Result(Ok<ValueType> value) : value(value.value), isError(false) {}

        /**
         * Create a new return value with an error.
         * @param error The error code.
         */
        // NOLINTNEXTLINE(google-explicit-constructor)
        Result(Err<ErrorType> error) : error(error.error), isError(true) {}

        ~Result() {
            if (isError) {
                error.~ErrorType();
            } else {
                value.~ValueType();
            }
        }

        /**
         * Get the value of the result. If there is an error
         * an exception will be thrown here.
         * @return The value.
         */
        ValueType get() {
            if (isError) {
                throw std::runtime_error("Result has error");
            }

            return value;
        }

        /**
         * Get the error. If there is no error an exception
         * will be thrown here.
         * @return The error code.
         */
        ErrorType getError() {
            if (!isError)
                throw std::runtime_error("Result has no error");

            return error;
        }
    };
}
