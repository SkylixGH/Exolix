#pragma once

namespace exolix {
    /**
     * A structure used for storing RGB values.
     */
    struct ColorRgb {
        /**
         * The red value.
         */
        unsigned short red = 250;

        /**
         * The green value.
         */
        unsigned short green = 0;

        /**
         * The blue value.
         */
        unsigned short blue = 0;

        /**
         * The alpha value.
         */
        unsigned short alpha = 250;
    };
}