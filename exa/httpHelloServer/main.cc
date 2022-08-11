#include <exolix.h>

namespace x {
    using exolix::net::server::http::Server;
}

int main() {
    x::Server server(3050, 8);

    server.spawn();
    server.block();

    return 0;
}
