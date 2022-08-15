#include <exolix/net.h>
#include <iostream>

using namespace exolix::net;
using namespace std;

int main() {
    SocketServer server;

    server.setOnSocketOpenListener([] (Socket &socket) {
        cout << "New connection: \n";
//        socket.setOnMessageListener([&socket] (SocketMessage &message) {
//            socket.send("You sent: " + message.toString());
//            socket.close();
//        });

        socket.block();
    });

    server.listen(3000);
    server.block();

    return 0;
}
