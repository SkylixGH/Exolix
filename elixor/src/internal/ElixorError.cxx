#include "ElixorError.hxx"
#include <iostream>

using namespace std;

namespace Elixor {
    namespace Internal {
        ElixorError::ElixorError(const string code, const string message) {
            this->code = code;
            this->message = message;
        }

        string ElixorError::what() { return code + " -> " + message; }

        string ElixorError::getReason() { return code; }
    } // namespace Internal
} // namespace Elixor
