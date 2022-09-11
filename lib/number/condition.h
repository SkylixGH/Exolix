#pragma once

#include <string>

namespace exolix {
    /**
     * A class used for performing conditionals on numbers.
     */
     class NumberCondition {
     public:
         /**
          * Check to see if a string is just an integer.
          * @param source The input string that should be checked.
          * @return If the string is just an integer.
          */
        static bool isInteger(std::string source);
     };
}
