#pragma once

#include <string>

namespace skylix::exolix::lexer {
    struct LexerToken {
        int lineStart;
        int lineEnd;
        int columnStart;
        int columnEnd;
        int characterStart;
        int characterEnd;
    };
}
