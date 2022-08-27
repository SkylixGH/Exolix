#include <exolix.hxx>

using namespace exolix;

enum Uw : int {
    UW
};

int main() {
    NetAddress ip(8080, "localhost");

    auto resolved = ip.getResolvedIp();

    return 0;
}
