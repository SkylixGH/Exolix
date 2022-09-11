#include "conversion.h"

namespace exolix {
    std::string StringConversion::toLowerCase(std::string source) {
        for (int i = 0; i < source.size(); i++) {
            source[i] = std::tolower(source[i]);
        }

        return source;
    }
}
