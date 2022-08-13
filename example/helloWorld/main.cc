#include <exolix/net.h>
#include <thread>
#include <iostream>

using namespace exolix::net;

int main() {
    SocketServer server(8080);

    server.setOnSocketOpen([&server] (Socket *socket) {
        std::cout << "Socket opened ID = " << socket->socketHandle << " " << std::endl;

        socket->setOnMessage([&socket] (const std::string& msg) {
            std::cout << "MSG by: " << socket->socketHandle << " -> " << msg << std::endl;
            socket->send("Hey, what's up!");
        });

        socket->block();

        std::cout << "MSG by: " << socket->socketHandle << " -> " << "Closed!" << std::endl;
    });

    std::thread t([&] () {
        try { server.bind(); } catch (SocketError &e) {
            e.render();
        }
    });

    t.join();
    return 0;
}
