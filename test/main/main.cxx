#include <exolix.hxx>

using namespace exolix;

int main() {
    NetAddress loc(8080);
    SocketServer server(loc);

    server.listen();
    server.block();

    return 0;
}
