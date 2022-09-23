#include <exolix>

int main() {
    using exolix::NetAddr;

    /**
     * Here we are checking to see if an invalid IPv6
     * address is identified as a valid addresses. If valid, then the test will FAIL.
     */
    bool valid1 = NetAddr::isIpv6HostnameValid("abc");

    if (valid1) {
        std::cerr << "Invalid claim, IPv6 address is valid: abc" << std::endl;
        return 1;
    }

    return 0;
}
