package net.skylix.elixor.terminal.color;

import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

import java.util.HashMap;

/**
 * A class used for parsing and converting colors to be rendering in the terminal.
 */
public class ColorUtil {
    /**
     * Identify if the color is RED, GREEN, BLUE, or BLACK.
     *
     * @param red   The red value of the color to identify.
     * @param green The green value of the color to identify.
     * @param blue  The blue value of the color to identify.
     * @return The color name.
     */
    public static ColorNamesGeneric identifyColor(Integer red, Integer green, Integer blue) {
        if (red > green && red > blue) {
            return ColorNamesGeneric.RED;
        } else if (green > red && green > blue) {
            return ColorNamesGeneric.GREEN;
        } else if (blue > red && blue > green) {
            return ColorNamesGeneric.BLUE;
        } else {
            return ColorNamesGeneric.BLACK;
        }
    }

    /**
     * Identify if the color is RED, GREEN, BLUE, or BLACK.
     *
     * @param hex The hex value of the color to identify.
     * @return The color name.
     */
    public static ColorNamesGeneric identifyColor(String hex) throws InvalidHexCode {
        final Integer[] rgb = ColorConversion.hexToRGB(hex);
        return identifyColor(rgb[0], rgb[1], rgb[2]);
    }

    /**
     * Find the closest RGB color in a list of colors.
     *
     * @param red    The red value of the color to find.
     * @param green  The green value of the color to find.
     * @param blue   The blue value of the color to find.
     * @param colors The list of colors to search through.
     * @return An integer array representing reg, green, and blue for the closest color.
     */
    public static Integer[] findClosestColor(Integer red, Integer green, Integer blue, Integer[][] colors) {
        Integer[] closest = new Integer[3];
        double distance = Double.MAX_VALUE;

        for (Integer[] color : colors) {
            double newDistance = Math.sqrt(Math.pow(red - color[0], 2) + Math.pow(green - color[1], 2) + Math.pow(blue - color[2], 2));
            if (newDistance < distance) {
                distance = newDistance;
                closest = color;
            }
        }

        return closest;
    }

    /**
     * Find the closest hex color in a list of colors.
     *
     * @param hex       The hex value of the color to find.
     * @param hexColors The list of colors to search through.
     * @return The hex value of the closest color.
     */
    public static String findClosestColor(String hex, String[] hexColors) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        final Integer[] rgbOfHex = ColorConversion.hexToRGB(hex);
        final HashMap<Integer, Integer[]> rgbOfColors = new HashMap();

        int index = 0;
        final int max = hexColors.length;

        for (index = 0; index < max; index++) {
            rgbOfColors.put(index, ColorConversion.hexToRGB(hexColors[index]));
        }

        Integer[] closest = findClosestColor(
                rgbOfHex[0],
                rgbOfHex[1],
                rgbOfHex[2],
                rgbOfColors.values().toArray(new Integer[rgbOfColors.size()][])
        );

        return ColorConversion.rgbToHex(closest[0], closest[1], closest[2], 1);
    }
}
