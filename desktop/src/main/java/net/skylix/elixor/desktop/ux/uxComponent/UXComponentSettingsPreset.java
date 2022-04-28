package net.skylix.elixor.desktop.ux.uxComponent;

import net.skylix.elixor.desktop.accessibility.Accessibility;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class UXComponentSettingsPreset {
    public Theme theme = new ThemeDark();

    public Accessibility accessibility = new Accessibility();

    public UXComponentSettingsPreset() throws InvalidHexCode {}
}
