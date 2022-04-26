package net.skylix.elixor.elixorFX.theming;

import java.util.HashMap;

public class Theming {
    private HashMap<String, ThemingColor> properties = new HashMap<>();

    public void setThemeAttribute(String key, ThemingColor value) {
        properties.put(key, value);
    }

    public ThemingColor getThemeAttribute(String key) {
        return properties.get(key);
    }
}
