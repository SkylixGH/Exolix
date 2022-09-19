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
    private:

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
        static void write(const std::string& text);

        /**
         * Set cursor position. This will move the printing location
         * to the specified location.
         * @param location The location to move the cursor to.
         */
        static void setCursorPos(const ConsoleLocation &location); // TODO: Relativity issue where 1 is actually 0

        /**
         * Move the CLI cursor relative to its current
         * printing position.
         * @param relativeLocation The relative location to move the cursor to.
         */
        static void moveCursor(const ConsoleLocation &relativeLocation);

        /**
         * Clear the current line the cursor is on.
         */
        static void clearLine();

        /**
         * Set the CLI cursor bar visibility.
         * @param visible True if the cursor bar should be visible.
         */
        static void setCursorBarVisible(bool visible);

        /**
         * Set the X position of the printing cursor.
         * @param x The X position to set the cursor to.
         */
        static void setCursorX(int x);

        /**
         * Set the Y position of the printing cursor.
         * @param y The Y position to set the cursor to.
         */
        static void setCursorY(int y);

        /**
         * Return the CLI cursor to home. We recommend calling
         * this before moving the cursor to allow for a clean
         * move and not have to worry about the cursor being
         * in a weird position.
         */
        static void returnCursor();
    };

    /**
     * Initialize the console environment.
     */
    void exolix_initConsole();
}
