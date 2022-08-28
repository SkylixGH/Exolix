#include <exolix.hxx>

using namespace exolix;

int main() {
//    SocketServer server(NetAddress(8082));
//
//    server.listen();
//    server.block();

    Thread uwu([] () {
        int seconds = 0;

        while (1) {
            std::cout << "Seconds: " << seconds << "\n";

            Thread::sleep(SleepUnit::SECONDS, 1);
            seconds++;
        }
    });

    uwu.startAndBlock();

    return 0;
}
