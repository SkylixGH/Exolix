#include "exolix/lexer/Lexer.hxx"
#include <regex>
#include <vector>
#include <exolix/lexer/LexerToken.hxx>

using std::string;
using std::vector;

namespace skylix::exolix::lexer {
    Lexer::Lexer() {
        //
    }

    Lexer::~Lexer() {
        //
    }

    vector<LexerToken> Lexer::process(const string * input) {
        return vector<LexerToken>();
    }
}
