package net.skylix.elixor.elixorFX.theming;

import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class ThemingColor {
    private Integer[] rgba;

    public ThemingColor(Integer red, Integer green, Integer blue, Integer alpha) {
        this.rgba = new Integer[]{red, green, blue, alpha};
    }

    public ThemingColor(String hexCode) throws InvalidHexCode {
        this.rgba = ColorConversion.hexToRGB(hexCode);
    }
}
