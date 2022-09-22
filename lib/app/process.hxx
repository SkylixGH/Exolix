#pragma once

#include <cstdint>

namespace exolix {
    /**
     * A class used for getting information about the current process,
     * and setting different configurations to this process.
     */
    class AppProcess {
    public:
        /**
         * Get the process id of the current process.
         * @return The process id of the current process.
         */
        static uint64_t getProcessId();

        /**
         * Block the program infinitely. This is used to keep the program
         * even if there is no work to be done. This block method can also
         * be called as many times as you want.
         */
        static void block();
    };
}
