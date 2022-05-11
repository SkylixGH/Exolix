package net.skylix.elixor.desktop.ux.uxButton;

import net.skylix.elixor.desktop.animation.AnimationColor;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabel;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabelSettings;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanel;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelColumnAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelRowAlignment;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelSettings;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXButton extends UXComponent {
    private final UXButtonSettings settings;
    private final AnimationColor animationColor;

    public UXButton(String label, UXButtonSettings settings) throws InvalidHexCode {
        super(settings.theme, settings.accessibility);

        this.settings = settings;
        UXButton self = this;
        UXLabel text = new UXLabel(label);

        UXPanel button = new UXPanel(new UXPanelSettings() {{
            width = text.getWidth() + 40;
            height = text.getHeight() + 10;
            cornerRadius = accessibility.radius1;
            rowAlignment = UXPanelRowAlignment.CENTER;
            columnAlignment = UXPanelColumnAlignment.CENTER;

            onMouseEnter = (panel) -> {
                self.animationColor.moveTo(theme.getThemeAttribute("component2"), accessibility.transitionSpeed5);
                text.setColor(theme.getThemeAttribute("text1"), accessibility.transitionSpeed5);
            };

            onMouseExit = (panel) -> {
                self.animationColor.moveTo(theme.getThemeAttribute("component1"), accessibility.transitionSpeed5);
                text.setColor(theme.getThemeAttribute("text4"), accessibility.transitionSpeed5);
            };

            onMouseClick = (panel) -> {
                settings.onMouseClick.accept(self);
            };
        }});

        button.setColor(theme.getThemeAttribute("component1"));
        button.add(text);

        this.animationColor = new AnimationColor(theme.getThemeAttribute("component1"), (acl, color) -> {
            button.setColor(color);
        });

        setElement(button);
    }

    public UXButton(String label) throws InvalidHexCode {
        this(label, new UXButtonSettings());
    }

    public UXButton() throws InvalidHexCode {
        this("...", new UXButtonSettings());
    }
}
