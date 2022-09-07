#include <exolix.hxx>

using namespace exolix;

int main() {
    SocketServer server(NetAddress("127.0.0.1", 8080));

    server.setOnAcceptListener([] (Socket &socket) {
        std::cout << "Accepted connection" << std::endl;
    });

    server.setOnPendingListener([] (int socketFd) {
        std::cout << "Pending connection" << std::endl;
    });

    server.listen();

    return 0;
}
