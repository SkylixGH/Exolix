package net.skylix.elixor.desktop.ux.uxPanel;

import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponentSettingsPreset;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.util.ArrayList;

public class UXPanelSettings extends UXComponentSettingsPreset {
    public ArrayList<UXComponent> elements;
    public int cornerRadius = 0;

    /**
     * The width of the panel, using null will set it to automatic.
     */
    public Integer width = null;

    /**
     * The height of the panel, using null will set it to automatic.
     */
    public Integer height = null;

    public UXPanelSettings() throws InvalidHexCode {}
}
