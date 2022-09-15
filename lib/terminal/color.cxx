#include "color.hxx"
#include "xt256.hxx"
#include "../algorithm/searches.hxx"

namespace exolix {
    std::string TerminalColor::rgbToAnsi(const ColorRgb &rgb) {
        auto [closest, index] = AlgorithmSearch::findClosestRgb(xt256Colors, rgb);
        return "\x1b[38;5;" + std::to_string(index) + "m";
    }

    std::string TerminalColor::hexToAnsi(const ColorHex &hex) {
        ColorRgb rgb {
            hex.getRed(),
            hex.getGreen(),
            hex.getBlue(),
            hex.getAlpha()
        };

        return rgbToAnsi(rgb);
    }
}
