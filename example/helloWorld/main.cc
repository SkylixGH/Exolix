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

            socket.send("You sent: ");
            socket.close();
        });

        socket.block();
        cout << "Connection closed: \n";
    });

    server.listen(3000);
    server.block();

    return 0;
}
