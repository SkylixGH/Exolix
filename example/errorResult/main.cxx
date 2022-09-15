#include "exolixs"
#include <string>

using namespace ex;

Result<std::string, int> work(const int &number) {
    if (number % 2 == 0) {
        return Ok<std::string>("Even");
    } else {
        return Err<int>(number);
    }
}

int main() {
    Result<std::string, int> res = work(5);

    if (res.isError) {
        std::cout << "Result: " << res.get() << std::endl;
    } else {
        std::cout << "Error: " << res.getError() << std::endl;
    }

    return 0;
}
