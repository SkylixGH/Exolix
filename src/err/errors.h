#pragma once

#include <exception>
#include <utility>
#include <string>

namespace exolix::err {
    /**
     * An exception class with an error code attachment.
     */
    template <typename CodeType>
    class Error : public std::exception {
    public:
        const CodeType code;
        const std::string message;

        Error(CodeType code, std::string message);
        std::string what();
        void render();
    };
}
