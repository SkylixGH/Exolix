#include <exolix.h>

using exolix::net::server::GatewayServer;
using exolix::net::server::Protocol;

int main() {
    GatewayServer server(Protocol::TCP, 8080, 8);
    return 0;
}
