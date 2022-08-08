#include <exolix/Compiler.hxx>
#include <string>
#include <iostream>
#include <filesystem>
#ifdef __unix__
#include <unistd.h>
#endif

using std::string;
using std::cout;
using std::filesystem::current_path;

namespace skylix::exolix {
    Compiler::Compiler(const std::string inputEntry) {
        pathToEntry = this->getCurrentCwd() + "/" + inputEntry;

        astGenerator = new ast::AstGenerator();
        lexer = new lexer::Lexer();
    }

    Compiler::~Compiler() {

    }

    void Compiler::compile() {

    }

    void Compiler::panic(const std::string message) {
        cout << "\x1b[31m" "[ Error ] " "\x1b[0m" << message << "\n";
        cout << "\x1b[31m" "[ Error ] " "\x1b[0m" << "The compiler has stopped, with exit code 1" << "\n";

        exit(1);
    }

    void Compiler::warn(const std::string message) {
        cout << "\x1b[93m" "[ Warning ] " "\x1b[0m" << message << "\n";
    }

    void Compiler::validate() {
        if (!std::filesystem::exists(this->pathToEntry)) {
            this->panic("Entry path at " + this->grayDim(this->pathToEntry) + " does not exist.");
        }

        if (!std::filesystem::is_regular_file(this->pathToEntry)) {
            this->panic("Entry path at " + this->grayDim(this->pathToEntry) + " is not a file.");
        }

        if (!this->pathToEntry.ends_with(".lx")) {
            this->warn("Entry path at " + this->grayDim(this->pathToEntry) + " is not a .lx file.");
        }
    }

    std::string Compiler::grayDim(const std::string input) {
        return "\x1b[90m" + input + "\x1b[0m";
    }

    std::string Compiler::getCurrentCwd() {
        return current_path();
    }

    long long int Compiler::getInputSize() {
        return std::filesystem::file_size(this->pathToEntry);
    }
}
