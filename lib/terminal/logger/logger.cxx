#include "logger.hxx"

namespace exolix {
    Result<nullptr_t, LoggerErrors> Logger::validate() {
        if (TerminalWidgetRuntime::isRunning())
            return Err(LoggerErrors::WIDGET_RUNNING);

        return Ok(nullptr);
    }

    Result<nullptr_t, LoggerErrors> Logger::info(const std::string &message) {
        auto result = validate();
        if (result.isError) return result;

        Console::write(LoggerBackend::messageFormat(
            TerminalColor(message, ColorRgb { 150, 150, 150 }).render(),
            "INFO:",
            ColorRgb { 200, 200, 200 },
            "•"
        ) + "\n");

        return Ok(nullptr);
    }

    Result<nullptr_t, LoggerErrors> Logger::success(const std::string &message) {
        auto result = validate();
        if (result.isError) return result;

        Console::write(LoggerBackend::messageFormat(
            TerminalColor(message, ColorRgb { 150, 150, 150 }).render(),
            "OK:",
            ColorRgb { 83, 255, 83 },
            "•"
        ) + "\n");

        return Ok(nullptr);
    }

    Result<nullptr_t, LoggerErrors> Logger::error(const std::string &message) {
        auto result = validate();
        if (result.isError) return result;

        Console::write(LoggerBackend::messageFormat(
            TerminalColor(message, ColorRgb { 255, 83, 63 }).render(),
            "ERROR:",
            ColorRgb { 255, 83, 63 },
            "•"
        ) + "\n");

        return Ok(nullptr);
    }

    Result<nullptr_t, LoggerErrors> Logger::warn(const std::string &message) {
        auto result = validate();
        if (result.isError) return result;

        Console::write(LoggerBackend::messageFormat(
            TerminalColor(message, ColorRgb { 150, 150, 150 }).render(),
            "WARN:",
            ColorRgb { 255, 255, 83 },
            "•"
        ) + "\n");

        return Ok(nullptr);
    }
}