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
            put("layerSolid1", new ThemeColor("#202020"));
            put("layerSolid2", new ThemeColor("#272727"));
            put("layerSolid3", new ThemeColor("#313131"));
            put("layerSolid4", new ThemeColor("#353535"));

            put("highlight1", new ThemeColor("#315a6e"));
            put("highlight2", new ThemeColor("#40758f"));
            put("highlight3", new ThemeColor("#5499ba"));
            put("highlight4", new ThemeColor("#6DD1FF"));

            put("highlightText1", new ThemeColor("#274859"));
            put("highlightText2", new ThemeColor("#1f3845"));
            put("highlightText3", new ThemeColor("#111f26"));
            put("highlightText4", new ThemeColor("#202020"));

            put("text1", new ThemeColor("#BDBDBD"));
            put("text2", new ThemeColor("#D9D9D9"));
            put("text3", new ThemeColor("#F1F1F1"));
            put("text4", new ThemeColor("#FFFFFF"));

            put("component1", new ThemeColor("#3D3D3D"));
            put("component2", new ThemeColor("#484848"));
            put("component3", new ThemeColor("#565656"));
            put("component4", new ThemeColor("#656565"));
        }});
    }
}
