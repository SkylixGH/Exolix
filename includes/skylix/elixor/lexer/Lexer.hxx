#pragma once

#include <vector>
#include <iostream>
#include <skylix/elixor/lexer/Token.hxx>

using namespace std;

namespace Skylix::Elixor {
    class Lexer {
    private:
        /**
         * @brief The original lexer data.
         */
        string data;

        /**
         * @brief All of the lexer searched tokens.
         */
        vector<Token> tokens;

    public:
        vector<Token> GetTokens();

        /**
         * @brief Construct a new Lexer object
         * 
         * @param data The lexer input data.
         */
        Lexer(const string data);
    };
}
