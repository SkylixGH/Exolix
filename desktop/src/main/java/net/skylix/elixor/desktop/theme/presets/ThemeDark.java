package net.skylix.elixor.desktop.theme.presets;

import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.awt.*;
import java.util.HashMap;

public class ThemeDark extends Theme {
    public ThemeDark() throws InvalidHexCode {
        super();

        setThemeAttributes(new HashMap<>() {{
            put("layerSolid1", new ThemeColor("#121212"));
            put("layerSolid2", new ThemeColor("#181818"));
            put("layerSolid3", new ThemeColor("#1C1C1C"));
            put("layerSolid4", new ThemeColor("#202020"));

            put("highlight1", new ThemeColor("#49849F"));
            put("highlight2", new ThemeColor("#55A4C9"));
            put("highlight3", new ThemeColor("#66BDE6"));
            put("highlight4", new ThemeColor("#6DD1FF"));

            put("text1", new ThemeColor("#BDBDBD"));
            put("text2", new ThemeColor("#D9D9D9"));
            put("text3", new ThemeColor("#F1F1F1"));
            put("text4", new ThemeColor("#FFFFFF"));

            put("dynamic1", new ThemeColor(255, 255, 255, alphaPercentToInt(5)));
            put("dynamic2", new ThemeColor(255, 255, 255, alphaPercentToInt(10)));
            put("dynamic3", new ThemeColor(255, 255, 255, alphaPercentToInt(20)));
            put("dynamic4", new ThemeColor(255, 255, 255, alphaPercentToInt(30)));
        }});
    }

    private Integer alphaPercentToInt(int alphaPercent) {
        return (int) ((255 * alphaPercent) / 100);
    }
}
