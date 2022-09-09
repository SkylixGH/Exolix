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
