#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    std::string p1 = "/path/to/";
    std::string p2 = "/my/folder/oh/go/../";

    std::cout << "Path[0] = " << p1 << std::endl;
    std::cout << "Path[1] = " << p2 << std::endl;

    std::string path = Path::join(p1, p2);
    std::cout << "Path = " << path << std::endl;

    return 0;
}
