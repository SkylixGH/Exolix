#pragma once

#include <string>

namespace skylix::exolix::lexer {
    struct Token {
        std::string match;
        std::string type;
    };
}
