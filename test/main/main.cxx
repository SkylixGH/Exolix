#include <exolix.hxx>

using namespace exolix;

int main() {
    std::vector<Thread*> pool {};

    WinsockTcpServer server([&](SOCKET socketFd) {

        Thread *xo = new Thread([&]() {
            char buffer[1024];
            int result = 0;

            do {
                std::cout << "Connection received" << socketFd << std::endl;
                result = recv(socketFd, buffer, sizeof(buffer), 0);

                if (result == 0) {
                    std::cout << "Closed" << socketFd << std::endl;
                    break;
                } else if (result == SOCKET_ERROR)
                    std::cout << "Cannot Receive: " << WSAGetLastError() << std::endl;
                else
                    std::cout << buffer << std::endl;

                std::cout << "Received: " << result << std::endl;
            } while ((result > 0));

            std::cout << "Closed" << socketFd << std::endl;
        });

        xo->startAndDetach();
        pool.push_back(xo);
    });

    server.listen("localhost", 8080);
    return 0;
}
