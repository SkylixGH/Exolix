#include "process.hxx"

#ifdef _WIN32
    #include <windows.h>
    #include <processthreadsapi.h>
#endif

namespace exolix {
    uint64_t AppProcess::getProcessId() {
#ifdef _WIN32
        return GetCurrentProcessId();
#endif

        // TODO: Implement for other platforms
    }

    void AppProcess::block() {
#ifdef _WIN32
        Sleep(INFINITE);
#endif

        // TODO: Implement for other platforms
    }
}
