#include "errors.h"
#include <iostream>
#include <utility>

namespace exolix::err {
    template <typename CodeType>
    Error<CodeType>::Error(CodeType inCode, std::string inMessage):
        message(std::move(inMessage)), code(std::move(inCode)) {}

    template <typename CodeType>
    std::string Error<CodeType>::what() {
        return "Error " + code + ": '" + message + "'";
    }

    template <typename CodeType>
    void Error<CodeType>::render() {
        const std::string escape = "\x1b";

        std::cout << escape << "[31m" "Error: " << escape << "[4m" << code << escape << "[0m "
            << message << escape << "[31m" "!" << "\n";
    }
}
