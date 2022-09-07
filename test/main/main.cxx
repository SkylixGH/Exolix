#include <exolix.hxx>
#include <vector>
#include <thread>

using namespace exolix;

int main() {
    std::vector<std::thread *> pool {};

    NetAddress address("127.0.0.1");

    bool inum = NetAddress::isValidIpv4Address(address.host);

    std::cout << inum << std::endl;

    UnixTcpServer server([&] (int fd) {
        std::cout << "Open: " << fd << std::endl;

        auto *thread = new std::thread([&] () {
            char buffer[65535];
            int resData;

            while ((resData = read(fd, buffer, 65535)) > 0) {
                std::cout << "Data: " << buffer << std::endl;
            }

            std::cout << "Done\n";
        });

        thread->detach();
        pool.push_back(thread);
    });

    try {
        server.listen("0.0.0.0asd", 8080);
    } catch (UnixTcpServerException &error) {
        error.render();
    }

    return 0;
}
