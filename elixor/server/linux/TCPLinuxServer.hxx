#pragma once

#include <iostream>

using namespace std;

namespace Elixor::Server::Linux {
    class TCPLinuxServer  {
    private:
        string host;
        int port;
        int serverFD;
        bool running;

        void HandleListeningSettingChanged();

    public:
        TCPLinuxServer(int port);

        void Start();
        void Stop();

        void SetAddress(string host, int port);

        pair<string, int> GetAddress();

        bool IsRunning();
    };
}
