#include <exolix>

int main() {
    using namespace exolix;

    NetAddr addr("::1"); // This should be valid
    auto hostRes = addr.getHost();

    if (hostRes.isError) {
        std::cerr << "Address returned an error when the hostname was valid" << std::endl;
        std::cerr << exolix_NetAddr_strerror(hostRes.getError()) << std::endl;
        return 1;
    }

    auto host = hostRes.get();

    NetAddr addr2("abc"); // This should be invalid
    auto hostRes2 = addr2.getHost();

    if (!hostRes2.isError) {
        std::cerr << "Address 2 did not return an error when fetching the host, when an error was expected" << std::endl;

        return 1;
    }

    NetAddr addr3("2001:0db8:85a3:0000:0000:8a2e:0370:7334"); // This should be valid
    auto hostRes3 = addr3.getHost();

    if (hostRes3.isError) {
        std::cerr << "Address 3 returned an error when the hostname was valid" << std::endl;
        std::cerr << exolix_NetAddr_strerror(hostRes3.getError()) << std::endl;

        return 1;
    }

    auto host3 = hostRes3.get();

    NetAddr addr4("127.0.0.1"); // This should be valid
    auto hostRes4 = addr4.getHost();

    if (hostRes4.isError) {
        std::cerr << "Address 4 returned an error when the hostname was valid" << std::endl;
        std::cerr << exolix_NetAddr_strerror(hostRes4.getError()) << std::endl;

        return 1;
    }

    auto host4 = hostRes4.get();

    std::cout << "Found host 1 [" << host << "]" << std::endl;
    std::cout << "Host 2 was invalid as expected, this is working correctly" << std::endl;
    std::cout << "Found host 3 [" << host3 << "]" << std::endl;
    std::cout << "Found host 4 [" << host4 << "]" << std::endl;

    return 0;
}
