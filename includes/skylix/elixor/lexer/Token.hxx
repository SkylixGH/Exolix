#pragma once

#include <iostream>

using namespace std;

namespace Skylix::Elixor {
    struct Token {
        /**
         * @brief The token value.
         */
        string value;

        /**
         * @brief The line that the token is on.
         */
        int line;

        /**
         * @brief The character column of the token.
         */
        int column;
        
        /**
         * @brief The character index of the token.
         * 
         */
        int index;
    };
}
