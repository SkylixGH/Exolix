package net.skylix.elixor.desktop.ux.uxPanel;

import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponentSettingsPreset;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.util.ArrayList;
import java.util.function.Consumer;

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

    public ThemeColor color = new ThemeColor(0, 0, 0, 0);

    public Consumer<UXPanel> onMouseEnter = (UXPanel node) -> {};
    public Consumer<UXPanel> onMouseExit = (UXPanel node) -> {};
    public Consumer<UXPanel> onMouseClick = (UXPanel node) -> {};
    public Consumer<UXPanel> onMouseDown = (UXPanel node) -> {};
    public Consumer<UXPanel> onMouseUp = (UXPanel node) -> {};

    public UXPanelColumnAlignment columnAlignment = UXPanelColumnAlignment.TOP;
    public UXPanelRowAlignment rowAlignment = UXPanelRowAlignment.LEFT;
    public UXPanelFlowDirection flowDirection = UXPanelFlowDirection.ROW;

    public UXPanelSettings() throws InvalidHexCode {}
}
