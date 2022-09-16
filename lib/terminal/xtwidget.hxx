#pragma once

namespace exolix {
    /**
     * A class used for creating terminal animation
     * or interactive widgets.
     */
    class TerminalXtWidget {
    public:
        virtual ~TerminalXtWidget() = default;

    public:
        /**
         * Render your widget contents.
         */
        virtual void render() = 0;

        /**
         * Handle the user key presses.
         * @param key The key pressed by the user.
         */
        virtual void handleKeyPress(const char key) = 0;
    };
}
