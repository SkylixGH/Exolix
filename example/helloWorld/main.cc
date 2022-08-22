#include <exolix/net.h>
#include <exolix/http.h>
#include <iostream>

using namespace exolix::net;
using namespace std;

int main() {
    SocketServer server({
        backlog: 10,
        certificateAt: "/home/xfaon/CLionProjects/Exolix22/example/helloWorld/cert/cert.pem",
        keyAt: "/home/xfaon/CLionProjects/Exolix22/example/helloWorld/cert/key.pem"
    });

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

    cout << "Online \n";
    server.block();

    return 0;
}
