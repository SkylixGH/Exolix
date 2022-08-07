#pragma once

#include <string>

namespace Skylix::Exolix::Lexer {
    struct LexerToken {
        int lineStart;
        int lineEnd;
        int columnStart;
        int columnEnd;
        int characterStart;
        int characterEnd;
    };
}
