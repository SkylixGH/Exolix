#include <exolix/net.h>
#include <iostream>

using namespace exolix::net;
using namespace std;

int main() {
    SocketServer server;

    server.setOnSocketOpenListener([] (Socket &socket) {
        cout << "New connection: \n";
        socket.setOnMessageListener([&socket] (SocketMessage &message) {
            cout << "Message: " << message.toString() << "\n";
            socket.send("Hello World!");
        });

        socket.block();
        cout << "Connection closed: \n";
    });

    try {
        server.listen(3000);
    } catch (SocketError &e) {
        e.render();
    }

    server.block();

    return 0;
}
