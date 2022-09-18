#pragma once

#include <string>
#include "../color/colors.hxx"
#include <optional>

namespace exolix {
    /**
     * This is a class used for converting hexadecimal to the closest supported
     * ANSI color.
     */
    class TerminalColor {
    private:
        /**
         * The original text.
         */
        std::string text;

        /**
         * The foreground color.
         */
        std::optional<ColorRgb> foreground;

        /**
         * The background color.
         */
        std::optional<ColorRgb> background;

        /**
         * Whether to bold the text.
         */
        bool bold;

        /**
         * Whether to underline the text.
         */
        bool underline;

        /**
         * Whether to italicize the text.
         */
        bool italic;

        /**
         * Whether to strike through the text.
         */
        bool strikeThrough;

        /**
         * Whether to blink the text.
         */
        bool blink;

    public:
        /**
         * The reset ansi code. This is used to reset the color back to the
         * default color.
         */
        static const std::string reset;

        /**
         * The bold ansi code. This is used to bold the text.
         */
        static const std::string boldCode;

        /**
         * The underline ansi code. This is used to underline the text.
         */
        static const std::string underlineCode;

        /**
         * The italic ansi code. This is used to italicize the text.
         */
        static const std::string italicCode;

        /**
         * The strike through ansi code. This is used to strike through the text.
         */
        static const std::string strikeThroughCode;

        /**
         * The blink ansi code. This is used to blink the text.
         */
        static const std::string blinkCode;

        /**
         * Create a new instance of a terminal text modification
         * for colors, style and others util.
         * @param text The text to decorate.
         */
        explicit TerminalColor(const std::string& text);

        /**
         * Convert an RGB color to the closest supported xt256 color
         * to be displayed in the terminal.
         * @param rgb The RGB color to convert.
         * @param background Whether to apply this color to the background.
         * @return The closest supported xt256 color.
         */
        static std::string rgbToAnsi(const ColorRgb &rgb, bool background = false);

        /**
         * Convert a hexadecimal color to the closest supported xt256 color
         * to be displayed in the terminal.
         * @param hex The hexadecimal color to convert.
         * @param background Whether to apply this color to the background.
         * @return The closest supported xt256 color.
         */
        static std::string hexToAnsi(const ColorHex &hex, bool background = false);

        /**
         * Render the text output. This will combine and compute all the ANSI
         * codes to return a string that can be printed to the terminal.
         * @return The rendered text.
         */
        std::string render();

        /**
         * Set the bold factor value. When enabled, the text will be bold
         * on render.
         * @param bold Whether to bold the text.
         */
        TerminalColor *setBold(bool bold);

        /**
         * Set the underline factor value. When enabled, the text will be
         * underlined on render.
         * @param underline Whether to underline the text.
         */
        TerminalColor *setUnderline(bool underline);

        /**
         * Set the italic factor value. When enabled, the text will be italic
         * on render.
         * @param italic Whether to italicize the text.
         */
        TerminalColor *setItalic(bool italic);

        /**
         * Set the strike through factor value. When enabled, the text will be
         * struck through on render.
         * @param strikeThrough Whether to strike through the text.
         */
        TerminalColor *setStrikeThrough(bool strikeThrough);

        /**
         * Set the blink factor value. When enabled, the text will be blinking
         * on render. Do note that this is not supported on all terminals and
         * sometimes will be completely ignored if a color or background is set.
         * @param blink Whether to blink the text.
         */
        TerminalColor *setBlink(bool blink);

        /**
         * Set the hex foreground color. This will set the foreground color
         * to the specified hex color.
         * @param hex The hex color to set.
         */
        TerminalColor *setFg(const std::optional<ColorHex> &hex = std::nullopt);

        /**
         * Set the hex background color. This will set the background color
         * to the specified hex color.
         * @param hex The hex color to set.
         */
        TerminalColor *setBk(const std::optional<ColorHex> &hex = std::nullopt);

        /**
         * Set the RGB foreground color. This will set the foreground color
         * to the specified RGB color.
         * @param rgb The RGB color to set.
         */
        TerminalColor *setFg(const std::optional<ColorRgb> &rgb = std::nullopt);

        /**
         * Set the RGB background color. This will set the background color
         * to the specified RGB color.
         * @param rgb The RGB color to set.
         */
        TerminalColor *setBk(const std::optional<ColorRgb> &rgb = std::nullopt);
    };
}
