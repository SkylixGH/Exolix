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

using namespace exolix::net;
using namespace exolix::str;

void log(const std::string& message) {
    std::cout << message << std::endl;
}

int main() {
    SocketServer server(8080);

    server.setOnSocketOpen([&server] (int id) {
        Socket socket(id);
        socket.setOnMessage([&socket] (SocketMessage message) {
            HttpHeaders heads(message.toString());

            log(heads.toString());

            socket.send(
                    "HTTP/1.1 200 OK\r\n"
                    "Content-Type: text/html; charset=UTF-8\r\n"
                    "Cache-Control: no-cache\r\n"
                    "\r\n"
                    "<html><body>Hello World!</body></html>"
            );

            socket.close();
        });

        socket.block();
    });

    server.bind();
    server.block();

    return 0;
}

