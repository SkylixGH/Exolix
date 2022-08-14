//#include <exolix/net.h>
//
//using namespace exolix::net;
//
//int main() {
//    HttpServer server; // BOUND TO 80, THIS SHOULD FAIL WITHOUT ROOT, ITS 100% NORMAL ON UNIX!!!
//
//    try { server.bind(); }
//    catch (SocketError &e) { e.render(); return -1; }
//
//    server.block();
//    return 0;
//}

#include <exolix/net.h>
#include <exolix/str.h>
#include <iostream>

using namespace exolix::net;
using namespace exolix::str;

void log(const std::string& message) {
    std::cout << message << std::endl;
}

int main() {
    HttpServer server(8080);

    server.setOnRequest([&] (HttpHeaders *request, HttpHeaders *response) {
        std::cout << "Request at " << request->getPath() << std::endl;
    });

    server.bind();
    server.block();

    return 0;
}

