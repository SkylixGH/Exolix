#pragma once

#include <string>

namespace skylix::exolix {
    class Compiler {
    public:
        std::string pathToEntry;

        Compiler(const std::string inputEntry);
        ~Compiler();

        void compile();
        void panic(const std::string message);
        void warn(const std::string message);
        void validate();

        std::string grayDim(const std::string input);
        std::string getCurrentCwd();
    };
}
