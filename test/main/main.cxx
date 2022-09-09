#include <exolix.hxx>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address(NetAddress::getAvailablePort(), "localhost");
    SocketServer server(address);

    server.setTls({
        cert: "/home/xfaon/CLionProjects/Exolix22/test/main/c.pem",
        key: "/home/xfaon/CLionProjects/Exolix22/test/main/k.pem"
    });

    server.setOnAcceptListener([&server] (Socket &socket) {
        std::cout << "Accepted connection" << std::endl;

        socket.send("Dispatch testing");

        socket.setOnReceiveListener([&socket, &server] (SocketMessage &msg) {
            if (StringCondition::contains(msg.toString(), "!shutdown")) {
                socket.send("Closing...");
                server.stop();

                return;
            }

            std::cout << "Received message: " << msg.toString() << std::endl;
            socket.send("Hi");
        });

        socket.block();
        std::cout << "Connection closed.." << std::endl;
    });

    try {
        server.listen();

        std::cout << "Online at " << address.getProcessed() << ":" << address.port << std::endl;
        server.block();
    } catch (SocketServerException &e) {
        e.render();
    }

    return 0;
}
