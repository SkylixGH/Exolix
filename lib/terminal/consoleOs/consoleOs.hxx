#pragma once

#include <functional>
#include "../../driver/hid/keyboard/structures.hxx"
#include <thread>

namespace exolix {
    /**
     * An abstract class that is to be used for implementing
     * native OS console utils such as key readers etc...
     * When constructed, this device is expected to boot and
     * start listening immediately.
     */
    class TerminalConsoleImpl {
    protected:
        /**
         * The key press listener.
         */
        std::function<void(DriverKeyboardEvent &event)> keyListener = [] (DriverKeyboardEvent &event) {};

        /**
         * Check to see if this was disposed.
         */
        bool isDisposed();

        /**
         * The actual listener body function.
         */
        virtual void listener();

    private:
        /**
         * Whether the listener is active.
         */
        bool disposed = false;

        /**
         * The listener thread.
         */
        std::thread *thread;

    public:
        TerminalConsoleImpl();

        virtual ~TerminalConsoleImpl();

        /**
         * Set the read key listener.
         * @param listener The key listener.
         */
        void setKeyListener(std::function<void(DriverKeyboardEvent &event)> listener);

        /**
         * Dispose the key listener.
         */
        void dispose();

        /**
         * Block the current thread until the listener is disposed.
         */
        void block();
    };
}
