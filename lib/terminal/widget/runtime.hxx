#pragma once

#include "../../result/result.hxx"
#include "../keyboard/keyListener.hxx"

namespace exolix {
    enum class TerminalWidgetRuntimeErrors;

    /**
     * A class used for creating terminal animation
     * or interactive widgets. This widget would run in an XT or XTerm
     * environment.
     */
    class TerminalXtWidget {
    protected:
        /**
         * Clean up the widget.
         */
        virtual void cleanUp() = 0;

        /**
         * Handle the user key presses.
         * @param event The key pressed by the user.
         */
        virtual void handleKeyPress(const DriverKeyboardEvent &event) = 0;

    private:
        /**
         * Whether this specific runtime is running. Its important to keep
         * track of this because the terminal runtime does not know what
         * widget instance is running.
         */
        bool active;

        friend class TerminalWidgetRuntime;

    public:
        /**
         * Create a new command line XTerm widget.
         */
        TerminalXtWidget();

        virtual ~TerminalXtWidget() = default;

        /**
         * Start the widget and run it via the widget runtime.
         * @return Error code otherwise nullptr for success.
         */
        Result<nullptr_t, TerminalWidgetRuntimeErrors> start();

        /**
         * Stop the widget and kill it via the widget runtime.
         * @return Error code otherwise nullptr for success.
         */
        Result<nullptr_t, TerminalWidgetRuntimeErrors> stop();

        /**
         * Block the widget and block it via the widget runtime.
         * @return Error code otherwise nullptr for success.
         */
        Result<nullptr_t, TerminalWidgetRuntimeErrors> block();
    };

    /**
     * List of errors from the TerminalWidgetRuntime class.
     */
    enum class TerminalWidgetRuntimeErrors {
        /**
         * Widget already running. This is usually returned when you
         * tried to run a widget when it was already running.
         */
        WIDGET_ALREADY_RUNNING,

        /**
         * The widget was already blocked and cannot be blocked
         * again without a new widget being set.
         */
        WIDGET_ALREADY_BLOCKED,

        /**
         * No widget was found that is running.
         */
        NO_RUNNING_WIDGET
    };

    /**
     * A runtime built in tool used for managing widgets in
     * the terminal interface.
     */
    class TerminalWidgetRuntime {
    private:
        /**
         * Whether the widget is running or not.
         */
        static bool active;

        /**
         * Whether the widget was blocked or not.
         */
        static bool blocked;

        /**
         * The actual widget that is running.
         */
        static TerminalXtWidget *widget;

        /**
         * Terminal keyboard util for listening to key events.
         */
        static ConsoleKeyboard *keyboardUtil;

    public:
        /**
         * Run a widget object. This will run the widget object until either the widget object
         * commits suicide or the runtime is tasked with killing it.
         * @param widgetObject The widgetObject to run.
         * @return The return status of the widgetObject, no value is returned, but on error
         * an error is attached.
         */
        static Result<nullptr_t, TerminalWidgetRuntimeErrors> run(TerminalXtWidget &widgetObject);

        /**
         * Kill the running widget object.
         * @return The error status or nullptr if successfully halted.
         */
        static Result<nullptr_t, TerminalWidgetRuntimeErrors> halt();

        /**
         * Block the outside thread while the widget xterm device
         * is still running.
         * @return The error status or nullptr if blocked successfully.
         */
        static Result<nullptr_t, TerminalWidgetRuntimeErrors> block();

        /**
         * Check to see if the widget runtime is running a widget.
         * @return Whether a widget is running.
         */
        static bool isRunning();
    };
}
