#pragma once

#include "runtime.hxx"
#include <optional>

namespace exolix {
    /**
     * Modes for the terminal progress bar.
     */
    enum class TerminalProgressMode {
        /**
         * The progress bar should not have a set value and should
         * be indicate that it is waiting.
         */
        UNDETERMINED,

        /**
         * The progress bar should have a set value and should
         * be indicate that it has a value.
         */
        DETERMINED
    };

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
         * The current frame of the undetermined animation.
         */
        short currentAnimationFrame;

        /**
         * Whether the anime thread while loop should
         * continue to run.
         */
        bool animeRunning;

        /**
         * The thread for running the undetermined animation.
         */
        std::thread *animeThread;

        /**
         * The end message. This message replaces the progress bar
         * when finished. If no value is provided here, the progress
         * bar will stay in its last state.
         */
        std::optional<std::string> endMessage;

        /**
         * Render the progress bar to the terminal output.
         */
        void render();

        /**
         * Clean up the progress bar.
         */
        void cleanUp() override;

        /**
         * The terminal key press event listener. The progress bar does not
         * require user input and this this method will not receive a true implementation.
         * Calling this method is completely useless.
         * @param event The event fired.
         */
        void handleKeyPress(const exolix::DriverKeyboardEvent &event) override;

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
         * @param mode Whether the progress is determined or not.
         */
        explicit TerminalProgress(TerminalProgressMode mode);

        ~TerminalProgress();

        /**
         * Update the current progress value. This will update the progress bar
         * and render it to the terminal output. Do note that when the progress bar
         * is set to an undetermined state, if this function is called, it will revert
         * back to a determined state and render the progress.
         * @param value The new value.
         */
        void update(long long value = 0);

        /**
         * Set the determined state. When set to false, the progress
         * bar will show a waiting animation indicating the task is not
         * yet ready to start.
         * @param enabled Whether to enable determined progress bar.
         */
        void setDeterminedState(bool enabled);

        /**
         * Set the end message. This message replaces the progress bar
         * when finished. If no value is provided here, the progress
         * bar will stay in its last state.
         * @param message The end message.
         */
        void setEndMessage(std::optional<std::string> message);
    };
}
