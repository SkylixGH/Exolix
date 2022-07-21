#include "ThisProcess.hxx"
#ifdef __linux__
#include <signal.h>
#include <unistd.h>
#elif _WIN32
#include <windows.h>
#endif

namespace Elixor {
    namespace System {
        namespace Process {
            bool ThisProcess::isElevated() {
#ifdef __linux__
                return geteuid() == 0;
#elif _WIN32
                bool fRet = false;
                HANDLE hToken = NULL;

                if (OpenProcessToken(GetCurrentProcess(), TOKEN_QUERY,
                                     &hToken)) {
                    TOKEN_ELEVATION elevation;
                    DWORD callbackSize = sizeof(TOKEN_ELEVATION);

                    if (GetTokenInformation(hToken, TokenElevation, &elevation,
                                            sizeof(elevation), &callbackSize)) {
                        fRet = elevation.TokenIsElevated;
                    }
                }

                if (hToken) {
                    CloseHandle(hToken);
                }

                return fRet;
#endif
            }

            void ThisProcess::die(const int exitCode) {
#ifdef __linux__
                kill(getpid(), exitCode);
#elif _WIN32
                ExitProcess(exitCode);
#endif
            }

            bool ThisProcess::isUnix() {
#ifdef _WIN32
                return false;
#else
                return true;
#endif
            }
        } // namespace Process
    }     // namespace System
} // namespace Elixor
