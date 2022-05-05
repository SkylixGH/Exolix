package net.skylix.elixor.desktop.theme;

import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.awt.*;

public class ThemeColor {
    private final Integer[] rgba;

    public ThemeColor(Integer red, Integer green, Integer blue, Integer alpha) {
        this.rgba = new Integer[]{red, green, blue, alpha};
    }

    public ThemeColor(Integer red, Integer green, Integer blue) {
        this.rgba = new Integer[]{red, green, blue, 255};
    }

    public ThemeColor(String hexCode) throws InvalidHexCode {
        this.rgba = ColorConversion.hexToRGB(hexCode);
    }

    public final Integer getRed() {
        return rgba[0];
    }

    public final Integer getGreen() {
        return rgba[1];
    }

    public final Integer getBlue() {
        return rgba[2];
    }

    public final Integer getAlpha() {
        return rgba[3];
    }

    public final void setRed(Integer red) {
        rgba[0] = red;
    }

    public final void setGreen(Integer green) {
        rgba[1] = green;
    }

    public final void setBlue(Integer blue) {
        rgba[2] = blue;
    }

    public final void setAlpha(Integer alpha) {
        rgba[3] = alpha;
    }

    private void applyNullRgb() {
        if (rgba[0] == null) {
            rgba[0] = 0;
        }

        if (rgba[1] == null) {
            rgba[1] = 0;
        }

        if (rgba[2] == null) {
            rgba[2] = 0;
        }

        if (rgba[3] == null) {
            rgba[3] = 255;
        }
    }

    public final Color getAwtColor() {
        applyNullRgb();
        return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
    }

    public final String getCssColor() {
        applyNullRgb();
        return String.format("rgba(%d, %d, %d, %f)", rgba[0], rgba[1], rgba[2], (float)rgba[3]);
    }
}
