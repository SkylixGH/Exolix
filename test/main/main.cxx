#include <exolix.hxx>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address(8080);
    SocketServer server(address);

    try {
        auto x = NumberParsing::parse("-9223372036854775807"); // 7 at end
        std::cout << x << std::endl;
    } catch (NumberParsingException &e) {
        e.render();
    }

    server.setOnAcceptListener([] (Socket &socket) {
        std::cout << "Accepted connection" << std::endl;
    });

    server.setOnPendingListener([] (int socketFd) {
        std::cout << "Pending connection" << std::endl;
    });

    // server.listen(); 

    return 0;
}
