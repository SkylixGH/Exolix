#pragma once

#include "../../driver/hid/keyboard/structures.hxx"

namespace exolix {
    /**
     * A class used for creating terminal animation
     * or interactive widgets. This widget would run in an XT or XTerm
     * environment.
     */
    class TerminalXtWidget {
    public:
        virtual ~TerminalXtWidget() = default;

        /**
         * Handle the user key presses.
         * @param event The key pressed by the user.
         */
        virtual void handleKeyPress(const DriverKeyboardEvent &event) = 0;
    };
}
