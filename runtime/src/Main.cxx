#include <iostream>
#include <skylix/elixor/lexer/Lexer.hxx>

using namespace std;

int main(int argc, char * argv[]) {
    std::cout << "Loading... ERT" << std::endl;
    auto e = new Skylix::Elixor::Lexer();

    e->Hi();

    return 0;
}
