#include <exolix/net.h>
#include <thread>

using namespace exolix::net;

int main() {
    SocketServer server(8080);

    std::thread t([&] () {
        server.bind();
    });

    t.join();
    return 0;
}
