#include "ThisProcess.hxx"
#ifdef __linux__
#include <unistd.h>
#include <signal.h>
#endif

namespace Elixor {
    namespace System {
        namespace Process {
            bool ThisProcess::isElevated() {
#ifdef __linux__
                return geteuid() == 0;
#endif
            }

            void ThisProcess::die(const int exitCode) {
                kill(getpid(), exitCode);
            }
        }
    }
}
