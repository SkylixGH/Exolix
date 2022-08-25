#include <exolix/http.h>
#include <cstdio>

using namespace exolix::http;
using namespace exolix::net;

int main() {
    SocketServer hs;
    SocketServer controlPanel;

    hs.listen(3000);
    controlPanel.listen(8090);

    controlPanel.setOnSocketOpenListener([&] (Socket &socket) {
        std::cout << "Control panel connected\n";

        socket.setOnMessageListener([&] (SocketMessage &message) {
            std::cout << ("> " + message.toString());

            if (message.toString() == "stop\r\n") {
                std::cout << " - Shutting down\n";

                socket.close();
                controlPanel.shutdown();
                hs.shutdown();

                std::cout << " - Shut down complete\n";
            }
        });

        socket.block();
        std::cout << "Control panel disconnected: WARNING\n";
    });

    hs.block();
    controlPanel.block(); // FIX NOT UNBLOCK ON STOP
    
    return 0;
}
