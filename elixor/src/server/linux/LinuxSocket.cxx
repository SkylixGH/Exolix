#include <elixor.hxx>

#ifdef __linux__
    #include <sys/socket.h>
    #include <netinet/in.h>
    #include <strings.h>
    #include <unistd.h>
    #include <stdio.h>
#endif

using namespace std;
using namespace Elixor::Internal;

namespace Elixor::Server::Linux {
    LinuxSocket::LinuxSocket(const string host, const int port) {
        #ifdef __linux__
            const int socketFD = socket(AF_INET, SOCK_STREAM, 0);
            int newSocketFD;
            int clientLength;

            struct sockaddr_in serverAddress;
            struct sockaddr_in clientAddress;

            if (socketFD < 0) {
                throw ElixorError("FAILED_SOCKET_FD", "Could not open the socket");
            }

            bzero((char *) &serverAddress, sizeof(serverAddress));

            serverAddress.sin_family = AF_INET;
            serverAddress.sin_addr.s_addr = INADDR_ANY;
            serverAddress.sin_port = htons(port);

            if (bind(socketFD, (struct sockaddr *) &serverAddress, sizeof(serverAddress)) < 0) {
                throw ElixorError("FAILED_SOCKET_BIND", "Could not bind the socket");
            }

            listen(socketFD, 5);

            while (true) {
                clientLength = sizeof(clientAddress);
                newSocketFD = accept(socketFD, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLength);

                if (newSocketFD < 0) {
                    throw ElixorError("FAILED_SOCKET_ACCEPT", "Could not accept the socket");
                }

                char buffer[256];
                bzero(buffer, 256);
                read(newSocketFD, buffer, 255);
                printf("Here is the message: %s\n", buffer);
                close(newSocketFD);
            }
        #endif
    }
}
