#pragma once

namespace Elixor {
    namespace System {
        namespace Process {
            /**
             * Class containing static methods for getting information and
             * manipulating the current process.
             */
            class ThisProcess {
            public:
                /**
                 * This method returns a boolean indicating whether this process
                 * is running under elevated permissions.
                 * @return True if this process is running under elevated
                 * permissions, false otherwise.
                 */
                static bool isElevated();

                /**
                 * This method kills the current process with an optional error
                 * code.
                 * @param exitCode The exit code to use when killing the
                 * process.
                 */
                static void die(const int exitCode = 0);

                /**
                 * This method checks to see if the current operating platform
                 * is UNIX based.
                 *
                 * @return True if the current operating platform is UNIX based,
                 * false otherwise.
                 */
                static bool isUnix();
            };
        } // namespace Process
    }     // namespace System
} // namespace Elixor