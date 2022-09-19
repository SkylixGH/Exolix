#pragma once

#include "../../result/result.hxx"
#include "../keyboard/keyListener.hxx"
#include "xtwidget.hxx"

namespace exolix {
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
    };
}
