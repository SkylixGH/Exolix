#include <exolix.hxx>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address(-1);
    SocketServer server(address);
    
    server.setOnAcceptListener([] (Socket &socket) {
        std::cout << "Accepted connection" << std::endl;
    });

    server.setOnPendingListener([] (int socketFd) {
        std::cout << "Pending connection" << std::endl;
    });

    server.listen(); 

    return 0;
}
