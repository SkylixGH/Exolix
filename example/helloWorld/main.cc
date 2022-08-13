#include <exolix/net.h>

using namespace exolix::net;

int main() {
    HttpServer server; // BOUND TO 80, THIS SHOULD FAIL WITHOUT ROOT, ITS 100% NORMAL ON UNIX!!!

    try { server.bind(); }
    catch (SocketError &e) { e.render(); return -1; }

    server.block();
    return 0;
}
