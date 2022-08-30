#include <exolix.hxx>

using namespace exolix;

int main() {
    UnixTcpServer server([] (int socketFd) {
        std::cout << "Connection handler called" << socketFd << std::endl;
    });

    server.listen("127.0.0.1", 8080);
    return 0;
}
