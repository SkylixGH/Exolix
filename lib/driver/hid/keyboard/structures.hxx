#pragma once

namespace exolix {
    /**
     * A datatype used for keyboard key events such as presses
     * and others.
     */
    struct DriverKeyboardEvent {
        /**
         * The keyboard key ID.
         */
        unsigned int id = 0;

        /**
         * Whether the left shift key is pressed.
         */
        bool lShift = false;

        /**
         * Whether the right shift key is pressed.
         */
        bool rShift = false;

        /**
         * Whether the left alt key is pressed.
         */
        bool lAlt = false;

        /**
         * Whether the right alt key is pressed.
         */
        bool rAlt = false;

        /**
         * Whether the left control key is pressed. On macos this is the
         * ⌘ key.
         */
        bool lCtrl = false;

        /**
         * Whether the right control key is pressed. On macos this is the
         * ⌘ key.
         */
        bool rCtrl = false;

        /**
         * Whether the meta key is pressed, in linux this is the menu key, on windows
         * this is the windows button and on macos this is the command key.
         */
        bool meta = false;

        /**
         * The key value.
         */
        char value = '\0';

        /**
         * Whether the key is pressed.
         */
        bool pressed = false;
    };

    /**
     * A enumeration dual type datatype used for determining whether
     * a key such as CTRL, ALT, SHIFT, etc... is on the left or right
     * side of the keyboard.
     */
    enum class DriverKeyboardSection {
        /**
         * The event was on the LEFT side of the keyboard.
         */
        LEFT,

        /**
         * The event was on the RIGHT side of the keyboard.
         */
        RIGHT
    };
}
