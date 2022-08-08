#pragma once

#include <vector>
#include <string>
#include <tuple>
#include "Token.hxx"
#include "TokenMeta.hxx"

namespace skylix::exolix::lexer {
    class Lexer {
    public:
        Lexer();
        ~Lexer();

        std::vector<std::tuple<Token, TokenMeta>> lex(const std::string * input);
    };
}
