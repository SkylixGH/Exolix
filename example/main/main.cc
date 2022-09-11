#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    NetAddress address("::1", 8080);
    SocketServer server(address, 1);

    auto lRes = server.load();

    if (lRes != SocketServerErrors::Ok) {
        std::cout << "Error load fail\n";

        switch (lRes) {
            case SocketServerErrors::FaultyAddress:
                std::cout << "Faulty address\n";
                break;
            case SocketServerErrors::ServerDangerousActionWhileOnline:
                std::cout << "Server is online\n";
                break;
            case SocketServerErrors::TlsNotEnabled:
                std::cout << "TLS is not enabled\n";
                break;
            default:
                std::cout << "Unknown error\n";
                break;
        }
        return 1;
    } else {
        std::cout << "Server loaded\n";
    }

    auto bRes = server.block();

    if (bRes != SocketServerErrors::Ok) {
        std::cout << "Error block fail\n";
        return 1;
    }

    return 0;
}