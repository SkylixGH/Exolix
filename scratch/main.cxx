#include <elixor.hxx>
#include <thread>
#include <chrono>

using namespace Elixor::System::Process;
using namespace Elixor::Server::WebSockets;
using namespace std;

int main() {
    WebSocketServer * wsServer = new WebSocketServer(8080);

    thread timer([&wsServer] () {
        std::this_thread::sleep_for(std::chrono::seconds(1));

        wsServer->stop();
    });

    wsServer->start();
    wsServer->block();

    cout << "This is after start";

    timer.join();
    return 0;
}
