#include "runtime.hxx"
#include "../keyboard/keyListener.hxx"

namespace exolix {
    TerminalXtWidget::TerminalXtWidget() : active(true) {}

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalXtWidget::start() {
        return TerminalWidgetRuntime::run(*this);
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalXtWidget::stop() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);

        auto res = TerminalWidgetRuntime::halt();
        if (res.isError) return res;
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalXtWidget::block() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);

        auto res = TerminalWidgetRuntime::block();
        if (res.isError) return res;
    }

    bool TerminalWidgetRuntime::active = false;
    bool TerminalWidgetRuntime::blocked = false;

    TerminalXtWidget *TerminalWidgetRuntime::widget = nullptr;
    ConsoleKeyboard *TerminalWidgetRuntime::keyboardUtil = nullptr;

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::run(exolix::TerminalXtWidget &widgetObject) {
        if (active) return Err(TerminalWidgetRuntimeErrors::WIDGET_ALREADY_RUNNING);
        active = true;
        blocked = false;

        keyboardUtil = new ConsoleKeyboard();
        widget = &widgetObject;

        keyboardUtil->setDriverListener([] (DriverKeyboardEvent &driverE) {
            widget->handleKeyPress(driverE);
        });

        return Ok(nullptr);
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::halt() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);

        keyboardUtil->dispose();
        widget = nullptr;

        blocked = false;
        active = false;
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::block() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);
        if (blocked) return Err(TerminalWidgetRuntimeErrors::WIDGET_ALREADY_BLOCKED);

        blocked = true;
        keyboardUtil->block();

        return Ok(nullptr);
    }
}
