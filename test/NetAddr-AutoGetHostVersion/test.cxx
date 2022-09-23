#include <exolix>

int main() {
    using namespace exolix;

    NetAddr addr("::1");                                      // This is valid
    NetAddr addr2("2001:0db8:85a3:0000:0000:8a2e:0370:7334"); // This is valid
    NetAddr addr3("127.0.0.1");                               // This is valid
    NetAddr addr4("abc");                                     // This is invalid

    auto vRes = addr.getVersion();
    auto vRes2 = addr2.getVersion();
    auto vRes3 = addr3.getVersion();
    auto vRes4 = addr4.getVersion();

    if (vRes != NetVer::INET_6) {
        std::cerr << "Address 1 is was not detected as IPv6 when the host was IPv6" << std::endl;
        return 1;
    }

    if (vRes2 != NetVer::INET_6) {
        std::cerr << "Address 2 is was not detected as IPv6 when the host was IPv6" << std::endl;
        return 1;
    }

    if (vRes3 != NetVer::INET_4) {
        std::cerr << "Address 3 is was not detected as IPv4 when the host was IPv4" << std::endl;
        return 1;
    }

    if (vRes4 != NetVer::INET_6) {
        std::cerr << "Address 4 (which is invalid) was not detected as the default value which is IPv6" << std::endl;
        return 1;
    }

    return 0;
}
