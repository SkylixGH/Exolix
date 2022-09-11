#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    i64 value;
    auto isValidHex = NumberParsing::parseHexInteger("-8000000000000000", value);
    std::cout << "Is valid hex: " << value << std::endl;

    if (isValidHex == NumberParsingErrors::Ok) {
        std::cout << "Is valid hex: " << "Yes" << std::endl;
    } else {
        std::cout << "Is not valid hex: " << "No" << std::endl;
    }

    NetAddress address("::18", 8080);
    SocketServer server(address, 10);

    server.load();
    server.block();

    return 0;
}