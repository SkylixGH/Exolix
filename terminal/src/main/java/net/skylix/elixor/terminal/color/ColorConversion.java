package net.skylix.elixor.terminal.color;

import java.awt.*;

/**
 * A class used for converting colors into different formats.
 */
public class ColorConversion {
    /**
     * Convert a hexadecimal color to an RGB color.
     * @param hex The hexadecimal color.
     * @return The RGB color.
     */
    public static Integer[] hexToRGB(String hex) {
        Color color = Color.decode(hex);

        return new Integer[]{
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                color.getAlpha()
        };
    }

    /**
     * Convert an RGB color to a hexadecimal color.
     * @param red The red RGB value.
     * @param green The green RGB value.
     * @param blue The blue RGB value.
     * @return The hexadecimal color.
     */
    public static String rgbToHex(Integer red, Integer green, Integer blue) {
        return String.format("#%02x%02x%02x", red, green, blue);
    }
}
