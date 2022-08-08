#include "exolix/lexer/Lexer.hxx"
#include <regex>
#include <tuple>
#include <vector>
#include <exolix/lexer/Token.hxx>

using std::string;
using std::vector;
using std::tuple;

namespace skylix::exolix::lexer {
    Lexer::Lexer() {

    }

    Lexer::~Lexer() {

    }

    vector<tuple<Token, TokenMeta>> Lexer::lex(const string * input) {
        vector<tuple<Token, TokenMeta>> tokens;

        return tokens;
    }
}
