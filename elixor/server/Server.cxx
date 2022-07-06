#include <elixor/server/Server.hxx>
#include <iostream>

using namespace std;

namespace Elixor::Server {
    Server::Server(int port) {
        this->port = port;
        this->host = "127.0.0.1";

#ifdef __linux__
        linuxServer = TCPLinuxServer(port);
#endif
    }

    Server::~Server() {
#ifdef __linux__
        linuxServer.Stop();
#endif
    }

    void Server::Start() {
#ifdef __linux__
        linuxServer.Start();
#endif
    }

    void Server::Stop() {
#ifdef __linux__
        linuxServer.Stop();
#endif
    }

    pair<string, int> Server::GetAddress() {
        return make_pair(host, port);
    }

    void Server::SetAddress(string host, int port) {
        this->host = host;
        this->port = port;

#ifdef __linux__
        linuxServer.SetAddress(host, port);
#endif
    }
}
