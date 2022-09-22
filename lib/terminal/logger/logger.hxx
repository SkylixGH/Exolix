#pragma once

#include "../widget/runtime.hxx"
#include "../../result/result.hxx"
#include "../console.hxx"
#include "backend.hxx"

namespace exolix {
    /**
     * Enumerated list of errors that can be returned by members
     * of the logger.
     */
    enum class LoggerErrors {
        /**
         * Widget currently running. A widget instance in the terminal
         * was detected running by the runtime. Messages cannot be logged
         * when a widget is running.
         */
        WIDGET_RUNNING,
    };

    /**
     * A class used for logging messages into the terminal.
     */
    class Logger {
    private:
        /**
         * Run the validator checks to run before logging something
         * into the terminal.
         * @return An error in the result, otherwise nullptr if nothing
         * went wrong.
         */
        static Result<nullptr_t, LoggerErrors> validate();

    public:
        /**
         * Log an informative message into the terminal. These are commonly used
         * for logging general information into the command line.
         * @param message The message to log.
         * @return A result type containing an error otherwise nullptr for the value.
         */
        static Result<nullptr_t, LoggerErrors> info(const std::string &message);

        /**
         * Log an error message into the terminal. These are commonly used
         * for logging errors into the command line.
         * @param message The message to log.
         * @return A result type containing an error otherwise nullptr for the value.
         */
        static Result<nullptr_t, LoggerErrors> error(const std::string &message);

        /**
         * Log a warning message into the terminal. These are commonly used
         * for logging warnings into the command line.
         * @param message The message to log.
         * @return A result type containing an error otherwise nullptr for the value.
         */
        static Result<nullptr_t, LoggerErrors> warn(const std::string &message);

        /**
         * Log a successive message into the terminal. These are commonly used
         * for logging success messages into the command line. The tags are
         * rendered as OK instead of SUCCESS to shorten the length of the tag
         * and to make the appearance more formal.
         * @param message The message to log.
         * @return A result type containing an error otherwise nullptr for the value.
         */
        static Result<nullptr_t, LoggerErrors> success(const std::string &message);
    };
}
