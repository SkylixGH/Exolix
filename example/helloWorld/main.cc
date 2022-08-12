#include <exolix/net.h>
#include <thread>

using namespace exolix::net;

int main() {
    SocketServer server(8080);

    std::thread t([&] () {
        try { server.bind(); } catch (SocketError &e) {
            e.render();
        }
    });

    t.join();
    return 0;
}
