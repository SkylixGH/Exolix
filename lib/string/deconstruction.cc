#include "deconstruction.h"
#include "../number/types.h"

namespace exolix {
    void StringDeconstruction::split(const std::string &source, const std::string &delimiter,
                                     std::vector<std::string> &result) {
        result.clear();

        if (delimiter.empty()) {
            for (int i = 0; i < source.size(); i++)
                result.push_back(source.substr(i, 1));

            return;
        }

        u32 start = 0;
        u32 end = source.find(delimiter);

        while ((int) end != std::string::npos) {
            result.push_back(source.substr(start, end - start));
            start = end + delimiter.size();
            end = source.find(delimiter, start);
        }

        result.push_back(source.substr(start));
    }
}
