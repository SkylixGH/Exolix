#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    i64 res;
    std::string input = "107b9";

    NumberParsingErrors ee = NumberParsing::parseInteger(input, res);

    std::cout << res << std::endl;

    switch (ee) {
        case NumberParsingErrors::Ok:
            std::cout << "Ok" << std::endl;
            break;

        case NumberParsingErrors::InvalidNumber:
            std::cout << "InvalidNumber" << std::endl;
            break;

        case NumberParsingErrors::NumberOutOfRange:
            std::cout << "NumberOutOfRange" << std::endl;
            break;

        case NumberParsingErrors::NotAnInteger:
            std::cout << "NotAnInteger" << std::endl;
            break;

        default:
            std::cout << "Unknown" << std::endl;
            break;
    }

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