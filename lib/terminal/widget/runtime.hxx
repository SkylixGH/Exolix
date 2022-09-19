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
         * The actual widget that is running.
         */
        static TerminalXtWidget *widget;

        /**
         * Terminal keyboard util for listening to key events.
         */
        static ConsoleKeyboard *keyboardUtil;

    public:
        /**
         * Run a widgetObject. This will run the widgetObject until either the widgetObject
         * commits suicide or the runtime is tasked with killing it.
         * @param widgetObject The widgetObject to run.
         * @return The return status of the widgetObject, no value is returned, but on error
         * an error is attached.
         */
        static Result<nullptr_t, TerminalWidgetRuntimeErrors> run(TerminalXtWidget &widgetObject);
    };
}
