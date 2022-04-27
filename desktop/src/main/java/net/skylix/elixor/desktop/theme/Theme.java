package net.skylix.elixor.desktop.theme;

import java.util.HashMap;

public class Theme {
    private HashMap<String, ThemeColor> properties = new HashMap<>();

    public void setThemeAttribute(String key, ThemeColor value) {
        properties.put(key, value);
    }

    public void setThemeAttributes(HashMap<String, ThemeColor> properties) {
        this.properties = properties;
    }

    public ThemeColor getThemeAttribute(String key) {
        return properties.get(key);
    }
}
