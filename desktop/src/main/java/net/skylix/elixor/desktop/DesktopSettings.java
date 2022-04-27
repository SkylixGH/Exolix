package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class DesktopSettings {
    public Theme theme = new ThemeDark();

    public DesktopSettings() throws InvalidHexCode {}
}
