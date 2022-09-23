#include <exolix>

int main() {
    using exolix::NetAddr;

    /**
     * Here we are checking to see if a broken invalid IPv4 address is identified
     * as a valid address. If valid, then the test will FAIL.
     */
    bool valid = NetAddr::isIpv4HostnameValid("abx");
    return valid;
}
