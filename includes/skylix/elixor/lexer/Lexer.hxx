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
        /**
         * @brief Get all of the tokens that were processed by the lexer.
         *
         * @return A vector of all of the tokens that were processed by the lexer.
         */
        vector<Token> GetTokens();

        /**
         * @brief Get a token from the lexer at a specific index.
         *
         * @param index The index of the token to get.
         * @return The token at the specified index.
         */
        Token GetToken(int index);

        /**
         * @brief Get a token from the lexer at a specific column and line.
         *
         * @param column The column of the token to get.
         * @param line The line of the token to get.
         * @return The token at the specified column and line.
         */
        Token GetToken(int column, int line);

        /**
         * @brief Construct a new Lexer object
         * 
         * @param data The lexer input data.
         */
        explicit Lexer(string data);
    };
}
