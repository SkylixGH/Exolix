#pragma once

#include "runtime.hxx"

namespace exolix {
    /**
     * A class used for creating terminal progress bars.
     */
    class TerminalProgress : public TerminalXtWidget {
    private:
        /**
         * The current progress value.
         */
        long long curr;

        /**
         * The maximum cap value of the progress bar.
         */
        long long reach;

        /**
         * Whether the progress bar has a determined value.
         */
        bool determined;

        /**
         * Render the progress bar to the terminal output.
         */
        void render();

        /**
         * Clean up the progress bar.
         */
        void cleanUp() override;

    public:
        /**
         * Create a new terminal XTerm based progress bar.
         * @param value The default base value.
         * @param max The maximum value of the progress bar.
         */
        explicit TerminalProgress(long long value = 0, long long max = 100);

        /**
         * Create a new terminal progress bar but with a busy
         * state indicating the task for determining the progress
         * is not in its ready function state.
         * @param determined Whether the progress is determined or not.
         */
        explicit TerminalProgress(std::false_type determined);

        /**
         * The terminal key press event listener. The progress bar does not
         * require user input and this this method will not receive a true implementation.
         * Calling this method is completely useless.
         * @param event The event fired.
         */
        void handleKeyPress(const exolix::DriverKeyboardEvent &event) override;

        /**
         * Update the current progress value. This will update the progress bar
         * and render it to the terminal output.
         * @param value The new value.
         */
        void update(long long value = 0);
    };
}
