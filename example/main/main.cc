#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    auto isValid = NumberCondition::isHexInteger("10FF");
    std::cout << "Is valid: " << isValid << std::endl;

    return 0;

    NetAddress address("2001:db8:3333:4444:5555:6666:7777:888899999", 8080);
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