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
        char id;

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
    };
}
