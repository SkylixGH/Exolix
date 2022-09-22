#include <exolix>

int main() {
    using exolix::NetAddr;

    /**
     * Here we are checking to see if a group of valid IPv6
     * addresses are identified as a valid addresses. If valid, then the test will pass.
     */
    bool valid1 = NetAddr::isIpv6HostnameValid("::1");
    bool valid2 = NetAddr::isIpv6HostnameValid("2001:0db8:85a3:0000:0000:8a2e:0370:7334");

    if (!valid1) {
        std::cerr << "Failed to validate valid IPv6 address: ::1" << std::endl;
        return 1;
    }

    if (!valid2) {
        std::cerr << "Failed to validate valid IPv6 address: 2001:0db8:85a3:0000:0000:8a2e:0370:7334" << std::endl;
        return 1;
    }

    return 0;
}
