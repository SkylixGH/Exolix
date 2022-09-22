#pragma once

#include <string>

namespace exolix {
    class ColorHex;

    struct ColorRgb;

    /**
     * A class used for storing and converting hexadecimal
     * strings.
     */
    class ColorHex {
    private:
        /**
         * The red value of the color.
         */
        unsigned short red;

        /**
         * The green value of the color.
         */
        unsigned short green;

        /**
         * The blue value of the color.
         */
        unsigned short blue;

        /**
         * The alpha value of the color.
         */
        unsigned short alpha;

    public:
        /**
         * Create a new hexadecimal color from an rgb amount.
         * @param red The red value of the color.
         * @param green The green value of the color.
         * @param blue The blue value of the color.
         * @param alpha The alpha value of the color.
         */
        ColorHex(unsigned short red = 0, unsigned short green = 0, unsigned short blue = 0, unsigned short alpha = 255);

        /**
         * Create a new hexadecimal color from a string.
         * @param hex The hexadecimal string to convert.
         */
        ColorHex(const std::string &hex);

        /**
         * Get the RGB color from the hexadecimal color.
         * @return The RGB color.
         */
        ColorRgb getRgb() const;

        /**
         * Get the HEX color from this instance.
         */
        std::string getHex() const;

        /**
         * Get the red color value.
         * @return The red color value.
         */
        unsigned short getRed() const;

        /**
         * Get the green color value.
         * @return The green color value.
         */
        unsigned short getGreen() const;

        /**
         * Get the blue color value.
         * @return The blue color value.
         */
        unsigned short getBlue() const;

        /**
         * Get the alpha color value.
         * @return The alpha color value.
         */
        unsigned short getAlpha() const;
    };

    /**
     * A structure used for storing RGB values.
     */
    struct ColorRgb {
        /**
         * The red value.
         */
        unsigned short red = 0;

        /**
         * The green value.
         */
        unsigned short green = 0;

        /**
         * The blue value.
         */
        unsigned short blue = 0;

        /**
         * The alpha color.
         */
        unsigned short alpha = 255;

        /**
         * Convert the to a hexadecimal object.
         * @return The hexadecimal object.
         */
        ColorHex getHex() const;
    };
}
