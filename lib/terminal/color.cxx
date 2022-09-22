#include <utility>

#include "color.hxx"
#include "xt256.hxx"
#include "../algorithm/searches.hxx"

namespace exolix {
    const std::string TerminalColor::boldCode = "\033[1m";
    const std::string TerminalColor::underlineCode = "\033[4m";
    const std::string TerminalColor::italicCode = "\033[3m";
    const std::string TerminalColor::reset = "\033[0m";
    const std::string TerminalColor::strikeThroughCode = "\033[9m";
    const std::string TerminalColor::blinkCode = "\033[5m";

    TerminalColor::TerminalColor(std::string text) :
            text(std::move(text)), blink(false), strikeThrough(false), italic(false), underline(false), bold(false) {
    }

    TerminalColor::TerminalColor(const std::string &text, const exolix::ColorHex &fgColor) :
            text(text), foreground(fgColor.getRgb()), blink(false), strikeThrough(false), italic(false),
            underline(false), bold(false) {
    }

    TerminalColor::TerminalColor(const std::string &text, const exolix::ColorRgb &fgColor) :
            text(text), foreground(fgColor), blink(false), strikeThrough(false), italic(false), underline(false),
            bold(false) {
    }

    std::string TerminalColor::rgbToAnsi(const ColorRgb &rgb, bool background) {
        auto [closest, index] = AlgorithmSearch::findClosestRgb(xt256Colors, rgb);

        if (background) {
            return "\x1b[48;5;" + std::to_string(index) + "m";
        } else {
            return "\x1b[38;5;" + std::to_string(index) + "m";
        }
    }

    std::string TerminalColor::hexToAnsi(const ColorHex &hex, bool background) {
        ColorRgb rgb{
                hex.getRed(),
                hex.getGreen(),
                hex.getBlue(),
                hex.getAlpha()
        };

        return rgbToAnsi(rgb, background);
    }

    TerminalColor *TerminalColor::setBold(bool bold) {
        this->bold = bold;
        return this;
    }

    TerminalColor *TerminalColor::setUnderline(bool underline) {
        this->underline = underline;
        return this;
    }

    TerminalColor *TerminalColor::setItalic(bool italic) {
        this->italic = italic;
        return this;
    }

    TerminalColor *TerminalColor::setStrikeThrough(bool strikeThrough) {
        this->strikeThrough = strikeThrough;
        return this;
    }

    TerminalColor *TerminalColor::setBlink(bool blink) {
        this->blink = blink;
        return this;
    }

    TerminalColor *TerminalColor::setFg(const std::optional<ColorHex> &hex) {
        this->foreground = hex->getRgb();
        return this;
    }

    TerminalColor *TerminalColor::setBk(const std::optional<ColorHex> &hex) {
        this->background = hex->getRgb();
        return this;
    }

    TerminalColor *TerminalColor::setFg(const std::optional<ColorRgb> &rgb) {
        this->foreground = rgb;
        return this;
    }

    TerminalColor *TerminalColor::setBk(const std::optional<ColorRgb> &rgb) {
        this->background = rgb;
        return this;
    }

    std::string TerminalColor::render() {
        std::string result;

        if (bold) {
            result += boldCode;
        }

        if (underline) {
            result += underlineCode;
        }

        if (italic) {
            result += italicCode;
        }

        if (strikeThrough) {
            result += strikeThroughCode;
        }

        if (blink) {
            result += blinkCode;
        }

        if (foreground.has_value()) {
            result += rgbToAnsi(foreground.value(), false);
        }

        if (background.has_value()) {
            result += rgbToAnsi(background.value(), true);
        }

        return result + text + reset;
    }
}
