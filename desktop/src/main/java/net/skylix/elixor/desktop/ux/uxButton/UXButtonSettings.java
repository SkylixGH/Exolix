package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.ux.uxComponent.UXComponentSettingsPreset;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import java.util.function.Consumer;

public class UXButtonSettings extends UXComponentSettingsPreset {
    public UXButtonType type = UXButtonType.GENERIC;
    public Consumer<UXButton> onMouseClick = (UXButton node) -> {};
    public Consumer<UXButton> onMouseEnter = (UXButton node) -> {};
    public Consumer<UXButton> onMouseExit = (UXButton node) -> {};
    public Consumer<UXButton> onMouseDown = (UXButton node) -> {};
    public Consumer<UXButton> onMouseUp = (UXButton node) -> {};

    public UXButtonSettings() throws InvalidHexCode {}
}
