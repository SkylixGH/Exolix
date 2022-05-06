package net.skylix.elixor.desktop.ux.uxLabel;

import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponentSettingsPreset;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class UXLabelSettings extends UXComponentSettingsPreset {
    public ThemeColor color = new ThemeColor("#FFFFFF");

    public int fontSize = 13;

    public UXLabelSettings() throws InvalidHexCode {}
}
