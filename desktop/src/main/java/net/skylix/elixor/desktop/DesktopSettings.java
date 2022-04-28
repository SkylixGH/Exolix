package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.accessibility.Accessibility;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class DesktopSettings {
    public Theme theme = new ThemeDark();

    public Accessibility accessibility = new Accessibility();

    public DesktopSettings() throws InvalidHexCode {}
}
