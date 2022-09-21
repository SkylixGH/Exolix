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
        return res;
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalXtWidget::block() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);

        auto res = TerminalWidgetRuntime::block();
        return res;
    }

    bool TerminalWidgetRuntime::active = false;
    bool TerminalWidgetRuntime::blocked = false;

    TerminalXtWidget *TerminalWidgetRuntime::widget = nullptr;
    ConsoleKeyboard *TerminalWidgetRuntime::keyboardUtil = nullptr;

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::run(exolix::TerminalXtWidget &widgetObject) {
        if (active) return Err(TerminalWidgetRuntimeErrors::WIDGET_ALREADY_RUNNING);

        keyboardUtil = new ConsoleKeyboard();
        widget = &widgetObject;

        keyboardUtil->setDriverListener([] (const DriverKeyboardEvent &driverE) {
            if (widget != nullptr) widget->handleKeyPress(driverE);
        });

        blocked = false;
        active = true;

        return Ok(nullptr);
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::halt() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);

        widget->cleanUp();
        if (keyboardUtil != nullptr) keyboardUtil->dispose();

        widget = nullptr;
        blocked = false;
        active = false;

        return Ok(nullptr);
    }

    Result<nullptr_t, TerminalWidgetRuntimeErrors> TerminalWidgetRuntime::block() {
        if (!active) return Err(TerminalWidgetRuntimeErrors::NO_RUNNING_WIDGET);
        if (blocked) return Err(TerminalWidgetRuntimeErrors::WIDGET_ALREADY_BLOCKED);

        blocked = true;
        if (keyboardUtil != nullptr) keyboardUtil->block();

        return Ok(nullptr);
    }

    bool TerminalWidgetRuntime::isRunning() {
        return active;
    }
}
