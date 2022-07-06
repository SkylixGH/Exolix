#pragma once

#include <elixor/server/linux/TCPLinuxServer.hxx>
#include <iostream>

using namespace std;

namespace Elixor::Server {
    using namespace Elixor::Server::Linux;

    class Server {
    private:
        TCPLinuxServer linuxServer = NULL;
        int port;
        string host;

    public:
        Server(const int port);
        ~Server();

        void Start();
        void Stop();

        void SetAddress(const string host, const int port);
        pair<string, int> GetAddress();
    };
}
