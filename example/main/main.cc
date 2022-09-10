#include <exolix>

using namespace exolix;

int main() {
    NetAddress address("localhost", 8080);

    std::string print;

    print += "Address: " + address.getHostname() + "\n";
    print += "Port: " + std::to_string(address.getPort()) + "\n";
    print += "Full: " + address.getFull() + "\n";

    printf("%s", print.c_str());

    return 0;
}