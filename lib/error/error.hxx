/*
 * Error messages and error codes are defined here.
 */

#pragma once

#include <optional>
#include <iostream>
#include <exception>
#include <utility>

namespace exolix {
    template<typename CodeType>
    class Error : public std::exception {
    public:
        const CodeType code;
        const std::string message;

        Error(CodeType inCode, std::string inMessage) :
                message(std::move(inMessage)), code(std::move(inCode)) {}

        std::string what() {
            return "Error " + code + ": '" + message + "'";
        }

        void render() {
            const std::string escape = "\x1b";

            std::cout << escape << "[31m" "Error: " << escape << "[4m" << (bool) code << escape << "[0m "
                      << message << escape << "[31m" "!" << escape << "[0m" "\n";
        }
    };
}
