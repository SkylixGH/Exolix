#pragma once

#include <string>

namespace exolix {
    /**
     * A location structure.
     */
    struct ConsoleLocation {
        /**
         * The x position.
         */
        int x;

        /**
         * The y position.
         */
        int y;
    };

    /**
     * This class is used for interfacing with the console interface.
     */
    class Console {
    public:
        /**
         * Check to see if the console supports ANSI escape codes.
         * @return True if the console supports ANSI escape codes.
         */
        static bool supportsAnsi();

        /**
         * Clear the console window.
         */
        static void clear();

        /**
         * Write a string to the console. This will be through the standard
         * out channel.
         * @param text The text to write to the console.
         */
        static void write(std::string text);

        /**
         * Set cursor position. This will move the printing location
         * to the specified location.
         * @param location The location to move the cursor to.
         */
        static void setCursorPos(const ConsoleLocation &location);

        /**
         * Clear the current line the cursor is on.
         */
        static void clearLine();
    };

    /**
     * Initialize the console environment.
     */
    void exolix_initConsole();
}
