#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    std::string hex = "FFFFFFFFFFFFFFFF";
    i64 hexValue;

    auto errorParsed = NumberParsing::parseHexInteger(hex, hexValue);
    std::cout << "Hex value: " << hexValue << std::endl;

    switch (errorParsed) {
        case NumberParsingErrors::Ok:
            std::cout << "Parsed successfully" << std::endl;
            break;
        case NumberParsingErrors::InvalidNumber:
            std::cout << "Invalid number" << std::endl;
            break;
        case NumberParsingErrors::NumberOutOfRange:
            std::cout << "Number out of range" << std::endl;
            break;
    }

    return 1;

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