#pragma once

#include <iostream>

namespace Elixor {
    namespace Internal {
        class ElixorError : public std::exception {
        private:
            std::string code;
            std::string message;

        public:
            ElixorError(const std::string code, const std::string message);
            std::string what();
            std::string getReason();
        };
    } // namespace Internal
} // namespace Elixor
