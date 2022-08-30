#include <exolix.hxx>

using namespace exolix;

int main() {
    UnixTcpServer server([&server] (int socketFd) {
        std::cout << "Connection handler called" << socketFd << std::endl;

        server.send(socketFd, "Hello world", 11);

        std::cout << "Threads: " << server.getActiveSockets() << "\n";
    });

    server.listen("127.0.0.1", 8080);
    return 0;
}
