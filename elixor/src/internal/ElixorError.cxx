#include "ElixorError.hxx"
#include <iostream>

using std::string;
using std::to_string;

namespace Elixor {
    namespace Internal {
        ElixorError::ElixorError(const string code, const string message) {
            this->code = code;
            this->message = message;
        }

        ElixorError::ElixorError(const int code, const string message) {
            this->code = to_string(code);
            this->message = message;
        }

        string ElixorError::what() { return code + " -> " + message; }

        string ElixorError::getReason() { return code; }
    } // namespace Internal
} // namespace Elixor
