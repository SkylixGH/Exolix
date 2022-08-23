#include <exolix/net.h>
#include <exolix/http.h>
#include <exolix/str.h>
#include <exolix/gate.h>
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
            std::cout << message.toString();
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

    exolix::gate::TestThing<int> tt;
    auto u = tt.geti();

    cout << "Online \n";
    server.block();

    return 0;
}
