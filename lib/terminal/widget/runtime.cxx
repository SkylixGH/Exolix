#include "runtime.hxx"
#include "../keyboard/keyListener.hxx"

namespace exolix {
    bool TerminalWidgetRuntime::active = false;
    TerminalXtWidget *TerminalWidgetRuntime::widget = nullptr;
    ConsoleKeyboard *TerminalWidgetRuntime::keyboardUtil = nullptr;

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::run(exolix::TerminalXtWidget &widgetObject) {
        if (active) return Err(TerminalWidgetRuntimeErrors::WIDGET_ALREADY_RUNNING);
        active = true;

        keyboardUtil = new ConsoleKeyboard();
        widget = &widgetObject;

        keyboardUtil->setDriverListener([] (DriverKeyboardEvent &driverE) {
            widget->handleKeyPress(driverE);
        });

        return Ok(nullptr);
    }
}
