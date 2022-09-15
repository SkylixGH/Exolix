#pragma once

#include <string>
#include "../color/rgb.hxx"

namespace exolix {
    /**
     * This is a class used for converting hexadecimal to the closest supported
     * ANSI color.
     */
    class TerminalColor {
    public:
        /**
         * Convert an RGB color to the closest supported xt256 color
         * to be displayed in the terminal.
         * @param rgb The RGB color to convert.
         * @return The closest supported xt256 color.
         */
        static std::string rgbToAnsi(const ColorRgb &rgb);
    };
}
