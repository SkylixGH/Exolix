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
    Lexer * lex = new Lexer("Hi There\nhi");

    const int line = 1;
    const int column = 100;

    cout << "Char at " << line << ":" << column << " is " << lex->GetToken(column, line).value << endl;

    delete lex;
    return 0;
}
