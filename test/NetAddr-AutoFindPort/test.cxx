#include <exolix>

int main() {
    using namespace exolix;

    exolix_initSockets();
    NetAddr addr(0); // The port should be auto selected.

    auto portRes = addr.getPort();

    if (portRes.isError) {
        switch (portRes.getError()) {
            case NetAddrErrors::UNKNOWN_ERROR_FINDING_PORT:
                std::cerr << "Unknown error finding port" << std::endl;
                break;

            case NetAddrErrors::UNAVAILABLE_OS_PORTS:
                std::cerr << "OS could not find a port that was available for listening" << std::endl;
                break;
        }

        return 1;
    }

    auto port = portRes.get();
    std::cout << "Found free port: " << port << std::endl;

    return 0;
}
