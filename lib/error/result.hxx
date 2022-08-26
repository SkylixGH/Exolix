#pragma once

#include <optional>
#include <iostream>
#include <exception>
#include <utility>

namespace exolix {
    template <typename Keys>
    class Error {
    public:
        const Keys reason;
        const std::string message;

        explicit Error(Keys keyReason = 0, std::string supportingMessage = "Unspecified"):
            reason(keyReason), message(std::move(supportingMessage)) {
        }
    };

    template <typename SafeReturn, typename ErrorKeys>
    class Result {
    private:
        SafeReturn value {};

        void renderError() {
            std::cout << "An error occurred, please navigate to your debugger!\n";
            std::cout << "If you are not using a debugger, please use one like GDB, or LLDB.\n";

            std::cout << "\nMessage: '" << error.message << "'\n";
        }

    public:
        const bool hasError;
        const Error<ErrorKeys> error;

        explicit Result(SafeReturn returnData):
            value(returnData), hasError(false), error(Error<ErrorKeys>()) {}

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
