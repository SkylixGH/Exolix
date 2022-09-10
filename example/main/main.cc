#include <exolix>

using namespace exolix;

int main() {
    NetAddress address("localhost", 8080);
    SocketServer server(address, 8080);

    server.load();
    return 0;
}