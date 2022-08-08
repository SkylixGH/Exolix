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
        pathToEntry = inputEntry;
    }

    Compiler::~Compiler() {

    }

    void Compiler::compile() {
        cout << this->getCurrentCwd() << "\n";
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
        const string precisePath = this->getCurrentCwd() + "/" + pathToEntry;

        if (!std::filesystem::exists(precisePath)) {
            this->panic("Entry path at " + this->grayDim(precisePath) + " does not exist.");
        }

        if (!std::filesystem::is_regular_file(precisePath)) {
            this->panic("Entry path at " + this->grayDim(precisePath) + " is not a file.");
        }

        if (!precisePath.ends_with(".lx")) {
            this->warn("Entry path at " + this->grayDim(precisePath) + " is not a .lx file.");
        }
    }

    std::string Compiler::grayDim(const std::string input) {
        return "\x1b[90m" + input + "\x1b[0m";
    }

    std::string Compiler::getCurrentCwd() {
        return current_path();
    }
}
