#include <skylix/elixor/lexer/Lexer.hxx>
#include <skylix/elixor/strings/Strings.hxx>
#include <iostream>
#include <vector>

using namespace std;
using namespace Skylix::Elixor;

vector<Token> Lexer::GetTokens() {
    vector<Token> tokens;
    for (Token token : this->tokens) {
        tokens.push_back(token);
    }

    return tokens;
}

Token Lexer::GetToken(int index) {
    Token result;
    Token found = this->tokens[index];

    if (!found.value.empty()) {
        result.value = found.value;
        result.line = found.line;
        result.column = found.column;
    }

    return result;
}

Token Lexer::GetToken(int column, int line) {
    Token result;
    Token found;

    for (Token token : this->tokens) {
        if (token.line == line && token.column == column) {
            found = token;
            break;
        }
    }

    if (!found.value.empty()) {
        result.value = found.value;
        result.line = found.line;
        result.column = found.column;
    }

    return result;
}

Lexer::Lexer(const string data) {
    this->data = data;

    auto chars = Strings::Split(data, "");

    int column = 0;
    int index = 0;
    int line = 0;

    for (const auto& symbol : chars) {
        if (symbol == "\n") {
            line++;
            column = 0;
        } else {
            Token token = {};

            token.column = column;
            token.line = line;
            token.index = index;
            token.value = symbol;

            tokens.push_back(token);

            column++;
        }

        index++;
    }
}
