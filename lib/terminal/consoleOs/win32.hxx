#pragma once

#include "consoleOs.hxx"

namespace exolix {
    /**
     * A class used for listening to Win32 console key
     * events.
     */
    class TerminalConsoleWin32Impl : public TerminalConsoleImpl {
    private:
        /**
         * The key listener itself.
         */
        void listener() override;
    };
}
