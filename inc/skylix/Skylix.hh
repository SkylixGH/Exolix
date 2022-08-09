#pragma once

#include <exception>
#include <utility>
#include <string>

namespace skylix {
    class Error : public std::exception {
    private:
        std::string code;
        std::string message;

    public:
        Error(std::string code, std::string message);
        std::string what();
        std::string getReason();
        void render();
    };
}
