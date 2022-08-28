#include <exolix.hxx>

using namespace exolix;

int main() {
//    SocketServer server(NetAddress(8082));
//
//    server.listen();
//    server.block();

    Thread uwu([] () {
        int seconds = 0;

        while (true) {
            std::cout << "Seconds: " << seconds << "\n";

            Thread::sleep(SleepUnit::NANOSECONDS, 1);
            seconds++;
        }
    });

    uwu.startAndBlock();

    return 0;
}
