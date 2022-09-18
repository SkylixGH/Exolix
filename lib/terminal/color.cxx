#include "color.hxx"
#include "xt256.hxx"
#include "../algorithm/searches.hxx"

namespace exolix {
    const std::string TerminalColor::reset = "\033[0m";

    std::string TerminalColor::rgbToAnsi(const ColorRgb &rgb, bool background) {
        auto [closest, index] = AlgorithmSearch::findClosestRgb(xt256Colors, rgb);

        if (background) {
            return "\x1b[48;5;" + std::to_string(index) + "m";
        } else {
            return "\x1b[38;5;" + std::to_string(index) + "m";
        }
    }

    std::string TerminalColor::hexToAnsi(const ColorHex &hex, bool background) {
        ColorRgb rgb {
            hex.getRed(),
            hex.getGreen(),
            hex.getBlue(),
            hex.getAlpha()
        };

        return rgbToAnsi(rgb, background);
    }
}
