#include <exolix.h>

using exolix::net::server::LiveServer;
using exolix::net::server::Protocol;

int main() {
    LiveServer server(Protocol::TCP, 8080, 8);
    return 0;
}
