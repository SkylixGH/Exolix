#include <iostream>
#include <skylix/elixor/lexer/Lexer.hxx>
#include <skylix/elixor/strings/Strings.hxx>

using namespace std;
using namespace Skylix::Elixor;

class Game {
    public:
        void H() {

        }
};

int main(int argc, char * argv[]) {
    Lexer * lex = new Lexer("Hi There");

    delete lex;
    return 0;
}
