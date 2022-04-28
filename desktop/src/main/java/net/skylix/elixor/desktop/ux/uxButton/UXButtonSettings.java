package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.ux.uxComponent.UXComponentSettingsPreset;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

public class UXButtonSettings extends UXComponentSettingsPreset {
    public UXButtonType type = UXButtonType.GENERIC;
    public Runnable onMouseClick = () -> {};
    public Runnable onMouseEnter = () -> {};
    public Runnable onMouseExit = () -> {};
    public Runnable onMouseDown = () -> {};
    public Runnable onMouseUp = () -> {};

    public UXButtonSettings() throws InvalidHexCode {}
}
