#include <elixor.hxx>
#include <iostream>

using namespace std;

namespace Elixor::Internal {
    ElixorError::ElixorError(const string code, const string message) {
        this->code = code;
        this->message = message;
    }

    string ElixorError::what() {
        return code + " -> " + message;
    }

    string ElixorError::getReason() {
        return code;
    }
}
