#pragma once

#include <string>
#include <exolix/lexer/Lexer.hxx>
#include <exolix/ast/AstGenerator.hxx>

namespace skylix::exolix {
    class Compiler {
    public:
        std::string pathToEntry;

        lexer::Lexer * lexer;
        ast::AstGenerator * astGenerator;

        Compiler(const std::string inputEntry);
        ~Compiler();

        void compile();
        void panic(const std::string message);
        void warn(const std::string message);
        void validate();

        std::string grayDim(const std::string input);
        std::string getCurrentCwd();

        long long int getInputSize();
    };
}
