#include "net.hxx"
#include <cstdio>

#ifdef _WIN32
    #include <winsock2.h>
    #include <ws2tcpip.h>
#endif

namespace exolix {
    void exolix_initSockets() {
#ifdef _WIN32
        WSAData wsaData {};
        int result = WSAStartup(MAKEWORD(2, 2), &wsaData);

        if (result != 0) {
            printf("WSAStartup failed: %d\n", result);
            exit(1);
        }
#endif

        // TODO: [Linux, Mac] Needs implementation
    }
}
