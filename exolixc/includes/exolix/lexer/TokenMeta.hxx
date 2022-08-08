#pragma once

namespace skylix::exolix::lexer {
    struct TokenMeta {
        int lineIn;
        int lineOut;

        int columnIn;
        int columnOut;

        int charIn;
        int charOut;
    };
}
