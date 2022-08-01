#include <elixor.hxx>
#include <thread>
#include <chrono>

using namespace Elixor::System::Process;
using namespace Elixor::Server::WebSockets;
using namespace std;

int main() {
    WebSocketServer * wsServer = new WebSocketServer(8080);

    wsServer->start();
    wsServer->block();
    return 0;
}
