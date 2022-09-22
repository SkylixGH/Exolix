#include <sstream>
#include <iomanip>
#include "colors.hxx"
#include "../algorithm/strings.hxx"

namespace exolix {
    ColorHex::ColorHex(const std::string &hex) {
        try {
            if (hex.length() == 3) {
                std::string newHex = "";
                for (int i = 0; i < 3; i++) {
                    newHex += hex[i];
                    newHex += hex[i];
                }

                red = std::stoi(newHex.substr(0, 2), nullptr, 16);
                green = std::stoi(newHex.substr(2, 2), nullptr, 16);
                blue = std::stoi(newHex.substr(4, 2), nullptr, 16);
                alpha = 255;
            } else if (hex.length() == 6) {
                red = std::stoi(hex.substr(0, 2), nullptr, 16);
                green = std::stoi(hex.substr(2, 2), nullptr, 16);
                blue = std::stoi(hex.substr(4, 2), nullptr, 16);
                alpha = 255;
            } else if (hex.length() == 8) {
                red = std::stoi(hex.substr(0, 2), nullptr, 16);
                green = std::stoi(hex.substr(2, 2), nullptr, 16);
                blue = std::stoi(hex.substr(4, 2), nullptr, 16);
                alpha = std::stoi(hex.substr(6, 2), nullptr, 16);
            } else {
                red = 0;
                green = 0;
                blue = 0;
                alpha = 255;
            }
        } catch (...) {
            red = 0;
            green = 0;
            blue = 0;
            alpha = 255;
        }
    }

    ColorHex::ColorHex(unsigned short red, unsigned short green, unsigned short blue, unsigned short alpha) {
        this->red = red;
        this->green = green;
        this->blue = blue;
        this->alpha = alpha;
    }

    ColorRgb ColorHex::getRgb() const {
        return ColorRgb{red, green, blue, alpha};
    }

    std::string ColorHex::getHex() const {
        std::stringstream stream;

        stream << std::hex << std::setfill('0') << std::setw(2) << red;
        stream << std::hex << std::setfill('0') << std::setw(2) << green;
        stream << std::hex << std::setfill('0') << std::setw(2) << blue;
        stream << std::hex << std::setfill('0') << std::setw(2) << alpha;

        return AlgorithmStrings::toUpper(stream.str());
    }

    unsigned short ColorHex::getRed() const {
        return red;
    }

    unsigned short ColorHex::getGreen() const {
        return green;
    }

    unsigned short ColorHex::getBlue() const {
        return blue;
    }

    unsigned short ColorHex::getAlpha() const {
        return alpha;
    }

    ColorHex ColorRgb::getHex() const {
        return ColorHex(red, green, blue, alpha);
    }
}
