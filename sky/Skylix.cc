#include <skylix/Skylix.hh>
#include <utility>
#include <iostream>

namespace skylix {
    Error::Error(std::string code1, std::string message1) {
        this->code = std::move(code1);
        this->message = std::move(message1);
    }

    std::string Error::what() { return code + " -> " + message; }

    std::string Error::getReason() { return code; }

    void Error::render() {
        std::cout << "Error: " << what() << std::endl;
    }
}
