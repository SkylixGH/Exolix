package net.skylix.elixor.terminal.color;

import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

import java.awt.*;

/**
 * A class used for converting colors into different formats.
 */
public class ColorConversion {
    /**
     * Convert a hexadecimal color to an RGB color.
     *
     * @param hex The hexadecimal color.
     * @return The RGB color.
     */
    public static Integer[] hexToRGB(String hex) throws InvalidHexCode {
        String hexColor = "";

        if (hex.startsWith("#")) {
            if (hex.length() == 4) {
                String hexCode2 = hex.substring(1, 4);
                hexColor = hex + hexCode2 + "FF";
            } else if (hex.length() == 6) {
                hexColor = hex + "FFF";
            } else if (hex.length() == 7) {
                hexColor = hex + "FF";
            } else {
                hexColor = hex;
            }

            if (hexColor.length() != 9) {
                throw new InvalidHexCode("The hex code " + hex + " is invalid and does not follow the correct format.");
            }
        } else {
            if (hex.length() == 3) {
                hexColor = hex + hex + "FF";
            } else if (hex.length() == 5) {
                hexColor = hex + hex + "FFF";
            } else if (hex.length() == 6) {
                hexColor = hex + "FF";
            } else {
                hexColor = hex;
            }

            if (hexColor.length() != 8) {
                throw new InvalidHexCode("The hex code " + hex + " is invalid and does not follow the correct format.");
            }
        }

        int red = Integer.parseInt(hexColor.substring(1, 3), 16);
        int green = Integer.parseInt(hexColor.substring(3, 5), 16);
        int blue = Integer.parseInt(hexColor.substring(5, 7), 16);
        int alpha = Integer.parseInt(hexColor.substring(7, 9), 16);

        return new Integer[]{red, green, blue, alpha};
    }

    /**
     * Convert an RGB color to a hexadecimal color.
     *
     * @param red   The red RGB value.
     * @param green The green RGB value.
     * @param blue  The blue RGB value.
     * @param alpha The alpha RGB value.
     * @return The hexadecimal color.
     */
    public static String rgbToHex(Integer red, Integer green, Integer blue, Integer alpha) throws InvalidRGBValues, InvalidRGBAlpha {
        if (
                red > 255 || red < 0 ||
                        green > 255 || green < 0 ||
                        blue > 255 || blue < 0
        ) {
            throw new InvalidRGBValues("One or more segments of the RGB color values are outside the range of 0 and 255.");
        }

        if (alpha > 1 || alpha < 0) {
            throw new InvalidRGBAlpha("The alpha value is outside the range of 0 and 1.");
        }

        Color color = new Color(red, green, blue, alpha);

        String buf = Integer.toHexString(color.getRGB());
        String hex = buf.substring(buf.length() - 6);

        if (hex.length() == 6) {
            return hex.toUpperCase() + "FF";
        }

        return hex.toUpperCase();
    }
}
