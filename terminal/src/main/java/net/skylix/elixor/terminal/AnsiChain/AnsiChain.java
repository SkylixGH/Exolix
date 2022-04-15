package net.skylix.elixor.terminal.AnsiChain;

import net.skylix.elixor.terminal.color.ColorUtil;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

/**
 * A class used for modification of text visual to be displayed on the terminal.
 */
public class AnsiChain {
    /**
     * If the text should render in bold.
     */
    private boolean bold = false;

    /**
     * If the text should render with an underline.
     */
    private boolean underline = false;

    /**
     * The color ID for the background.
     */
    private Integer bgColor = null;

    /**
     * The color ID for the foreground text.
     */
    private Integer fgColor = null;

    /**
     * If the text should render with italics.
     */
    private boolean italic = false;

    /**
     * Set the bold rendering state of the text.
     *
     * @param boldEnabled If the text should render in bold.
     */
    public void bold(boolean boldEnabled) {
        bold = boldEnabled;
    }

    /**
     * Enable bold text.
     */
    public void bold() {
        bold(true);
    }

    /**
     * Set the underline rendering state of the text.
     *
     * @param underlineEnabled If the text should render with an underline.
     */
    public void underline(boolean underlineEnabled) {
        underline = underlineEnabled;
    }

    /**
     * Enable text underline.
     */
    public void underline() {
        underline(true);
    }

    /**
     * Set the background color of the text with a hex value.
     *
     * @param hexColor The hex color code to use.
     */
    public void bgColor(String hexColor) throws InvalidRGBAlpha, InvalidHexCode, InvalidRGBValues {
        String hexBGMatch = ColorUtil.findClosestColor(hexColor, ColorsCLI256.getAllColors());
        bgColor = ColorsCLI256.getCode(hexBGMatch);
    }

    /**
     * Set the background color of the text with an RGB value.
     *
     * @param red   The red value of the color.
     * @param green The green value of the color.
     * @param blue  The blue value of the color.
     */
    public void bgColor(Integer red, Integer green, Integer blue) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Integer[] rgbMatch = ColorUtil.findClosestColor(
                red,
                green,
                blue,
                ColorsCLI256.getAllColorsRGB()
        );

        bgColor = ColorsCLI256.getCode(rgbMatch[0], rgbMatch[1], rgbMatch[2]);
    }

    /**
     * Set the text color with a hex value.
     *
     * @param hexColor The hex color code to use.
     */
    public void color(String hexColor) throws InvalidRGBAlpha, InvalidHexCode, InvalidRGBValues {
        String hexBGMatch = ColorUtil.findClosestColor(hexColor, ColorsCLI256.getAllColors());
        fgColor = ColorsCLI256.getCode(hexBGMatch);
    }

    /**
     * Set the text color with an RGB value.
     *
     * @param red   The red value of the color.
     * @param green The green value of the color.
     */
    public void color(Integer red, Integer green, Integer blue) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Integer[] rgbMatch = ColorUtil.findClosestColor(
                red,
                green,
                blue,
                ColorsCLI256.getAllColorsRGB()
        );

        fgColor = ColorsCLI256.getCode(rgbMatch[0], rgbMatch[1], rgbMatch[2]);
    }

    /**
     * Apply the current text styling properties to the text.
     *
     * @param inputText The text to apply the styling to.
     * @return The styled text for the terminal.
     */
    public String apply(String inputText) {
        return (fgColor != null
                ? "\u001b[38;5;" + fgColor + "m"
                : ""
        )
                + (bgColor != null
                ? "\u001b[48;5;" + bgColor + "m"
                : ""
        )
                + (bold
                ? "\u001b[1m"
                : ""
        )
                + (underline
                ? "\u001b[4m"
                : ""
        )
                + (italic
                ? "\u001b[3m"
                : ""
        )
                + inputText + "\u001b[0m";
    }
}
