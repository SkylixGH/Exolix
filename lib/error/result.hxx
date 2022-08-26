#pragma once

#include <optional>
#include <iostream>
#include <exception>

namespace exolix {
    template <typename Keys>
    class Error {
    private:
        Keys reason;

    public:
        explicit Error(Keys keyReason):
            reason(keyReason) {
        }
    };

    template <typename SafeReturn, typename ErrorKeys>
    class Result {
    private:
        SafeReturn value {};
        Error<ErrorKeys> error { 0 };

        void renderError() {
            std::cout << "An error occurred ";
        }

    public:
        const bool hasError;

        explicit Result(SafeReturn returnData):
            value(returnData), hasError(false) {}

        explicit Result(Error<ErrorKeys> errorData):
            error(errorData), hasError(true) {}

        SafeReturn get() {
            if (hasError) {
                renderError();
                throw std::exception("Program will now terminate with exit code (1)");
            }

            return value;
        }
    };
}
