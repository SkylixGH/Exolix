#include <exolix.hxx>

using namespace exolix;

Result<std::string, int> getThing() {
    return Result<std::string, int>(Error(1));
}

int main() {
    auto x = getThing();

    auto y = x.get();

    std::cout << y << "\n";
    return 0;
}
