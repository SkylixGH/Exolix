#include "backend.hxx"

namespace exolix {
    std::string
    LoggerBackend::messageFormat(const std::string &message, const std::string &tag, const exolix::ColorRgb &colorTag, const std::string &icon) {
        std::string out = TerminalColor(icon + " " + tag, colorTag).render();
        out += " " + message;

        return out;
    }
}
