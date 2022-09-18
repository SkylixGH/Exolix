#pragma once

#include "../../driver/hid/keyboard/structures.hxx"

namespace exolix {
    /**
     * A class used for creating terminal animation
     * or interactive widgets.
     */
    class TerminalXtWidget {
    protected:
        /**
         * Handle the user key presses.
         * @param event The key pressed by the user.
         */
        virtual void handleKeyPress(const DriverKeyboardEvent &event) = 0;

    public:
        virtual ~TerminalXtWidget() = default;
    };
}
