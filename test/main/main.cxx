#include <exolix.hxx>

using namespace exolix;

int main() {
//    UnixTcpServer server([&server] (int socketFd) {
//        std::cout << "Connection handler called" << socketFd << std::endl;
//
//        server.send(socketFd, "Hello world", 11);
//
//        std::cout << "Threads: " << server.getActiveSockets() << "\n";
//    });
//
//    server.listen("127.0.0.1", 8080);

    WinsockTcpServer server([&server] (SOCKET socketFd) {
        std::cout << "Connection handler called" << socketFd << std::endl;

        // read multithreaded
        std::thread readThread([&server, &socketFd] () {
            char buffer[1024];
            int bytesRead = 0;

            while ((bytesRead = recv(socketFd, buffer, 1024, 0)) > 0) {
                // resize buffer to remove useless chars
                buffer[bytesRead] = '\0';

                std::cout << "Bytes read: " << bytesRead << std::endl;
                std::cout << "Buffer: " << buffer << std::endl;

                char data[] = "Hi";

                WinsockTcpServer::send(socketFd, data, 2);
            }

            std::cout << "Closed\n";
            WinsockTcpServer::close(socketFd);
        });

        readThread.join();
    });

    server.listen("127.0.0.1", 21);
    return 0;
}
