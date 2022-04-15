package net.skylix.elixor.terminal.AnsiChain;

import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorUtil;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

import java.util.Arrays;

public class AnsiChain {
    private boolean bold = false;

    private boolean underline = false;

    private Integer bgColor = null;

    private Integer fgColor = null;

    private boolean italic = false;

    public void bold(boolean boldEnabled) {
        bold = boldEnabled;
    }

    public void bold() {
        bold(true);
    }

    public void underline(boolean underlineEnabled) {
        underline = underlineEnabled;
    }

    public void underline() {
        underline(true);
    }

    public void bgColor(String hexColor) throws InvalidRGBAlpha, InvalidHexCode, InvalidRGBValues {
        String hexBGMatch = ColorUtil.findClosestColor(hexColor, ColorsCLI256.getAllColors());
        bgColor = ColorsCLI256.getCode(hexBGMatch);
    }

    public void bgColor(Integer red, Integer green, Integer blue) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Integer[] rgbMatch = ColorUtil.findClosestColor(
                red,
                green,
                blue,
                ColorsCLI256.getAllColorsRGB()
        );

        bgColor = ColorsCLI256.getCode(rgbMatch[0], rgbMatch[1], rgbMatch[2]);
    }

    public void color(String hexColor) throws InvalidRGBAlpha, InvalidHexCode, InvalidRGBValues {
        String hexBGMatch = ColorUtil.findClosestColor(hexColor, ColorsCLI256.getAllColors());
        fgColor = ColorsCLI256.getCode(hexBGMatch);
    }

    public void color(Integer red, Integer green, Integer blue) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues {
        Integer[] rgbMatch = ColorUtil.findClosestColor(
                red,
                green,
                blue,
                ColorsCLI256.getAllColorsRGB()
        );

        fgColor = ColorsCLI256.getCode(rgbMatch[0], rgbMatch[1], rgbMatch[2]);
    }

    public String apply(String inputText) {
        System.out.println(fgColor);
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
                + inputText + "\u001b[0m";
    }
}
