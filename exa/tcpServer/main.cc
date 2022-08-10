#include <exolix.h>
#include <skylix.h>
#include <iostream>
#include <thread>

using exolix::net::server::low::SocketServer;

int main() {
    SocketServer server(65535, 8);

    server.setOnAccept([&server] (int socket) {
        std::cout << "Accepted socket: " << socket << std::endl << " Count: " << server.count() << std::endl;
    });

    server.setOnMessage([&server](int socket, const std::string& message) {
        std::cout << "Message from socket: " << socket << ": " << message << " Count: " << server.count() << std::endl;
    });

    server.setOnClose([&server](int socket) {
        std::cout << "Closed socket: " << socket << ": " << " Count: " << server.count() << std::endl;
    });

    try {
        server.bind();
    } catch (skylix::Error& e) {
        e.render();
    }

    return 0;
}
