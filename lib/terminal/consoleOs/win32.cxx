#include "win32.hxx"

#ifdef _WIN32
    #include <windows.h>
    #include <iostream>
#endif

namespace exolix {
    void TerminalConsoleWin32Impl::listener() {
        printf("Listener started for win32\n");

        while (!isDisposed()) {
            DriverKeyboardEvent event {};

#ifdef _WIN32
            // Wait for key
#endif
        }
    }
}
