#include <elixor/server/linux/TCPLinuxServer.hxx>

#ifdef __linux__
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <cstring>
#include <iostream>
#include <netdb.h>
#endif

using namespace std;

namespace Elixor::Server::Linux {
    TCPLinuxServer::TCPLinuxServer(int port) {
        this->port = port;
        this->host = "127.0.0.1";
        this->running = false;
    }

    void TCPLinuxServer::Start() {
        #ifndef __linux__
            return
        #endif

        if (running) return;
        running = true;

        #ifdef __linux__
            struct sockaddr_in serverAddress;
            struct hostent *he;

            char hostname[host.length() + 1];
            strcpy(hostname, host.c_str());

            if ((he = gethostbyname(hostname)) == NULL) {
                perror("gethostbyname");
                exit(1);
            }

            memcpy(&serverAddress.sin_addr, he->h_addr, he->h_length);

            serverAddress.sin_family = AF_INET;
            serverAddress.sin_port = htons(port);

            int newSocket;
            int opt = 1;
            int addrlen = sizeof(serverAddress);
            char buffer[1023] = { 0 };
            string message = "Hi";

            if ((serverFD = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
                perror("socket failed");
                exit(EXIT_FAILURE);
            }

            if (setsockopt(serverFD, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt)) < 0) {
                perror("setsockopt");
                exit(EXIT_FAILURE);
            }

            if (bind(serverFD, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) < 0) {
                perror("bind failed");
                exit(EXIT_FAILURE);
            }

            if (listen(serverFD, 3) < 0) {
                perror("listen");
                exit(EXIT_FAILURE);
            }

            while (true) {
                if ((newSocket = accept(serverFD, (struct sockaddr *)&serverAddress, (socklen_t*) &addrlen)) < 0) {
                    perror("accept");
                    exit(EXIT_FAILURE);
                } else {
                    cout << "Connection accepted" << endl;
                }

                read(newSocket, buffer, 1024);
                cout << "Received: " << buffer << endl;

                write(newSocket, message.c_str(), message.length());
                close(newSocket);
            }
        #endif
    }

    void TCPLinuxServer::Stop() {
        if (!running) return;
    }

    pair<string, int> TCPLinuxServer::GetAddress() {
        return make_pair(host, port);
    }

    void TCPLinuxServer::SetAddress(string host, int port) {
        this->port = port;
        this->host = host;

        HandleListeningSettingChanged();
    }

    bool TCPLinuxServer::IsRunning() {
        return running;
    }

    void TCPLinuxServer::HandleListeningSettingChanged() {
        if (running) {
            Stop();
            Start();
        }
    }
}
