package net.skylix.elixor.desktop.theme;

import java.util.HashMap;

public class Theme {
    private final HashMap<String, ThemeColor> properties = new HashMap<>();

    public final void setThemeAttribute(String key, ThemeColor value) {
        properties.put(key, value);
    }

    public void setThemeAttributes(HashMap<String, ThemeColor> properties) {
        for (String key : properties.keySet()) {
            this.properties.put(key, properties.get(key));
        }
    }

    public final ThemeColor getThemeAttribute(String key) {
        return properties.get(key);
    }
}
