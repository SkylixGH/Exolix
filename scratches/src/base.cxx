#include <elixor/server/Server.hxx>

using namespace Elixor::Server;

int main() {
    Server server(9090);

    server.SetAddress("lossdfcalhost", 9090);

    server.Start();
}
