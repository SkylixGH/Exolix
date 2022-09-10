# Class: `StringDeconstruction`
- **Include**: `<exolix>`
- **Location**: `lib/string/deconstruction.h`
- **Namespace**: `exolix`

A class used for disassembling strings into sub parts in multiple different ways.

# Example
In the following example, the string will be taken as input and the result
will be printed out in parts.

```cpp
#include <exolix>

using namespace exolix;

int main() {
    // Split the string
    std::string source = "Hello World Wide Web!";
    std::vector<std::string> result;

    StringDeconstruction::split(source, " ", result);

    // Render the in/out of the split
    printf("Input: %s\n", source.c_str());

    for (int i = 0; i < result.size(); i++)
        printf("Result[%d]: %s\n", i, result[i].c_str());

    return 0;
}
```

# Static Method: `split`
This method will split a given input into multiple slices based on the delimiter.

**Arguments**
 - `source` The input source to split
   - `const std::string &` from `<string>`
 - `delimiter` The delimiter to split the source with
   - `const std::string &` from `<string>`
 - `result` The result to write the split characters to
   - `std::vector<std::string> &` from `<vector> <string>`
