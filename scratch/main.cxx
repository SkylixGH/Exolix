#include <elixor.hxx>

using namespace Elixor::System::Process;
using namespace Elixor::Server::WebSocket;
using namespace std;

int main() {
    bool iuac = ThisProcess::isElevated();
    WebSocketBackend wsServer = new WebSocketBackend("0.0.0.0", 8080);

    cout << (iuac
        ? "The process is elevated"
        : "The process is NOT elevated") << "\n";

    return 0;
}
