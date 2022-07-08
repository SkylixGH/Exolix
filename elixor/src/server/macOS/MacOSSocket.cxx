#include <elixor.hxx>
#include <iostream>

#ifdef __APPLE__
    #include "TargetConditionals.h"

    #ifdef TARGET_OS_MAC
    #endif
#endif

using namespace std;

namespace Elixor::Server::MacOS {
    MacOSSocket::MacOSSocket() {
        #ifdef TARGET_OS_MAC
            cout << "Your on macOS";
        #endif

        #ifndef TARGET_OS_MAC
            cout << "Not on mac";
        #endif
    }
}
