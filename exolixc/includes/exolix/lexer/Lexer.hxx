#pragma once

#include <vector>
#include <string>
#include "LexerToken.hxx"

namespace Skylix::Exolix::Lexer {
    class Lexer {
    public:
        Lexer();
        ~Lexer();

        std::vector<LexerToken> process(const std::string &input);
    };
}
