#pragma once

#include <string>
#include "../color.hxx"

namespace exolix {
    /**
     * A tool commonly used by the logger front end to format
     * the log messages correctly.
     */
    class LoggerBackend {
    public:
        /**
         * Format a new message for logging with basic tag features.
         * @param message The message to be logged.
         * @param tag The tag status for the message such as INFO, ADVANCE, ERROR, etc...
         * @param colorTag The color for the status info tag.
         * @param icon The icon to use for the tag, this will render before the tag.
         * @return The entire formatted message.
         */
        static std::string messageFormat(const std::string &message, const std::string &tag, const ColorRgb &colorTag,
                                         const std::string &icon = "•");
    };
}
