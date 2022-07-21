#include <elixor.hxx>

using namespace Elixor::System::Process;
using namespace Elixor::Server::WebSockets;
using namespace std;

int main() {
    bool iuac = ThisProcess::isElevated();
    WebSocketServer * wsServer = new WebSocketServer(8080);

    cout << (iuac ? "The process is elevated" : "The process is NOT elevated")
         << "\n";

    wsServer->start();

    return 0;
}
