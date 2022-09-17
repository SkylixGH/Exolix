#pragma once

#include "../consoleOs/win32.hxx"

namespace exolix {
    /**
     * A class used for listening to key events in the command
     * line terminal interface.
     */
    class ConsoleKeyboard {
    private:
        /**
         * The actual listener for when a value key is pressed in ansi.
         */
        std::function<void(char value)> ansiListener = [] (char value) {};

        /**
         * The actual listener for when the CTRL key is pressed.
         */
        std::function<void(bool ctrl, DriverKeyboardSection side)> ctrlListener = [] (bool ctrl, DriverKeyboardSection side) {};

        /**
         * The actual listener for when the ALT key is pressed.
         */
        std::function<void(bool alt, DriverKeyboardSection side)> altListener = [] (bool alt, DriverKeyboardSection side) {};

        /**
         * The actual listener for when the SHIFT key is pressed.
         */
        std::function<void(bool shift, DriverKeyboardSection side)> shiftListener = [] (bool shift, DriverKeyboardSection side) {};

        /**
         * The actual listener for when the META key is pressed.
         */
        std::function<void(bool meta)> metaListener = [] (bool meta) {};

        /**
         * The actual OS native implementation of the keyboard.
         */
        TerminalConsoleImpl* impl;

    public:
        /**
         * Create a new key listener and other console keyboard util class.
         */
        ConsoleKeyboard();

        ~ConsoleKeyboard();

        /**
         * Set the listener for when a new ANSI char is inputted.
         * @param listener The listener.
         */
        void setAnsiListener(std::function<void(char value)> listener);

        /**
         * Set the listener for when the CTRL key is pressed.
         * @param listener The listener.
         */
        void setCtrlListener(std::function<void(bool ctrl, DriverKeyboardSection side)> listener);

        /**
         * Set the listener for when the ALT key is pressed.
         * @param listener The listener.
         */
        void setAltListener(std::function<void(bool alt, DriverKeyboardSection side)> listener);

        /**
         * Set the listener for when the SHIFT key is pressed.
         * @param listener The listener.
         */
        void setShiftListener(std::function<void(bool shift, DriverKeyboardSection side)> listener);

        /**
         * Set the listener for when the META key is pressed.
         * @param listener The listener.
         */
        void setMetaListener(std::function<void(bool meta)> listener);

        /**
         * Block the thread and wait until the key listener is disposed.
         */
        void block();

        /**
         * Dispose the key listener.
         */
        void dispose();
    };
}
