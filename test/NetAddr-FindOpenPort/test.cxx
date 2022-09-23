#include <exolix>

int main() {
    using namespace exolix;

    exolix_initSockets();

    /**
     * Here we are trying to find a port that is available for listening
     * on the operating system.
     */
    auto portRes = NetAddr::findOpenPort();

    if (portRes.isError) {
        switch (portRes.getError()) {
            case NetAddrErrors::UNKNOWN_ERROR_FINDING_PORT:
                std::cerr << "Unknown error finding port" << std::endl;
                break;

            case NetAddrErrors::UNAVAILABLE_OS_PORTS:
                std::cerr << "No available ports for listening" << std::endl;
                break;
        }

        return 1;
    }

    auto port = portRes.get();
    std::cout << "Found free port: " << port << std::endl;

    return 0;
}
