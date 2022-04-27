package net.skylix.elixor.desktop.theme;

import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.awt.*;

public class ThemeColor {
    private Integer[] rgba;

    public ThemeColor(Integer red, Integer green, Integer blue, Integer alpha) {
        this.rgba = new Integer[]{red, green, blue, alpha};
    }

    public ThemeColor(Integer red, Integer green, Integer blue) {
        this.rgba = new Integer[]{red, green, blue, 1};
    }

    public ThemeColor(String hexCode) throws InvalidHexCode {
        this.rgba = ColorConversion.hexToRGB(hexCode);
    }

    public Integer getRed() {
        return rgba[0];
    }

    public Integer getGreen() {
        return rgba[1];
    }

    public Integer getBlue() {
        return rgba[2];
    }

    public Integer getAlpha() {
        return rgba[3];
    }

    public Color getAwtColor() {
        return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
    }
}
