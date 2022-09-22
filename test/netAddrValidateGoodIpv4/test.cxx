#include <exolix>

int main() {
    using exolix::NetAddr;

    /**
     * Here we are checking to see if a valid IPv4 address is identified
     * as a valid address. If valid, then the test will pass.
     */
    bool valid = NetAddr::isIpv4HostnameValid("0.0.0.0");
    return valid ? 0 : 1;
}
