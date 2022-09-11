#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address("2001:db8:3333:4444:5555:6666:7777:8888", 80);
    SocketServer server(address, 8080);

    server.load();
    server.block();

    std::string hostname;
    auto e = address.getHostname(hostname);

    switch (e) {
        case NetAddressErrors::Ok:
            std::cout << "Hostname: " << hostname << std::endl;
            break;

        case NetAddressErrors::InvalidHostname:
            std::cout << "Invalid hostname" << std::endl;
            break;

        case NetAddressErrors::InvalidPort:
            std::cout << "Invalid port" << std::endl;
    }

    return 0;
}