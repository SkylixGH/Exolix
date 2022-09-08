#include <exolix.hxx>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address(8080);
    SocketServer server(address);

    server.setOnAcceptListener([](Socket &socket) {
        std::cout << "Accepted connection" << std::endl;

        socket.setOnReceiveListener([&socket] (SocketMessage &msg) {
            std::cout << "Received message: " << msg.toString() << std::endl;
            socket.send("Hi");
        });

        socket.block();
        std::cout << "Connection closed" << std::endl;
    });

    server.listen();

    return 0;
}
