#include <exolix.h>
#include <skylix.h>
#include <iostream>
#include <thread>

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

    std::thread t([&server] () {
        std::this_thread::sleep_for(std::chrono::seconds(1));
        std::cout << "Closing server" << std::endl;
        server.unbind();
    });

    try {
        server.bind();
    } catch (skylix::Error& e) {
        e.render();
    }

    t.join();
    return 0;
}
