#include <exolix/net.h>
#include <exolix/err.h>

using namespace exolix::err;

void x() {
    throw Error(1, "This ain't no UwU");
}

int main() {
    x();

    return 0;
}
