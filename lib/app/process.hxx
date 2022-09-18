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
    };
}
