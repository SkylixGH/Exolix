#include "xtwidget.hxx"

namespace exolix {
    bool TerminalWidgetRuntime::active = false;

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
}
