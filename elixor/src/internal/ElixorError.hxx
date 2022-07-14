#pragma once

#include <iostream>

using namespace std;

namespace Elixor {
    namespace Internal {
        class ElixorError : public exception {
        private:
            string code;
            string message;

        public:
            ElixorError(const string code, const string message);
            string what();
            string getReason();
        };
    } // namespace Internal
} // namespace Elixor
