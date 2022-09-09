#include <exolix.hxx>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address(NetAddress::getAvailablePort(), "localhost");
    SocketServer server(address);

//    server.setTls({
//        cert: "C:\\Users\\xfaon\\Desktop\\Exolix-1\\test\\main\\c.pem",
//        key: "C:\\Users\\xfaon\\Desktop\\Exolix-1\\test\\main\\k.pem"
//    });

    server.setOnAcceptListener([](Socket &socket) {
        std::cout << "Accepted connection" << std::endl;

        socket.send("Dispatch testing");

        socket.setOnReceiveListener([&socket] (SocketMessage &msg) {
            std::cout << "Received message: " << msg.toString() << std::endl;
            socket.send("Hi");
        });

        socket.block();
        std::cout << "Connection closed.." << std::endl;
    });

    try {
        server.listen();

        std::cout << "Online at 127.0.0.1:" << address.port << std::endl;
        server.block();
    } catch (SocketServerException &e) {
        e.render();
    }

    return 0;
}
