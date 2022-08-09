#include <exolix.h>
#include <skylix.h>
#include <iostream>

using exolix::net::server::low::SocketServer;
using exolix::net::server::low::Type;

int main() {
    SocketServer server(Type::TCP, "0.0.0.0", 65535, 8);

    server.setOnAccept([] (int socket) {
        std::cout << "Accepted socket: " << socket << std::endl;
    });

    server.setOnMessage([](int socket, std::string message) {
        std::cout << "Message from socket: " << socket << ": " << message;
    });

    server.setOnClose([](int socket) {
        std::cout << "Closed socket: " << socket << ": " << std::endl;
    });

    try {
        server.bind();
    } catch (skylix::Error& e) {
        e.render();
    }
    return 0;
}
