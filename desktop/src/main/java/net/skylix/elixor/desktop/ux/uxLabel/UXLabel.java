package net.skylix.elixor.desktop.ux.uxLabel;

import net.skylix.elixor.desktop.accessibility.Accessibility;
import net.skylix.elixor.desktop.animation.AnimationColor;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXLabel extends UXComponent {
    private final UXLabelSettings settings;
    private final AnimationColor animationColor;
    private final JLabel label;
    private String text;

    public UXLabel(String text, UXLabelSettings settings) {
        super(settings.theme, settings.accessibility);

        this.settings = settings;
        this.text = text;
        this.label = new JLabel();

        label.setText(text);
        label.setFont(new Font("Arial", Font.PLAIN, settings.fontSize));
        label.setForeground(settings.color.getAwtColor());

        animationColor = new AnimationColor(settings.color, (acl, color) -> {
            label.setForeground(color.getAwtColor());
        });

        setElement(label);

        getSwingComponent().setPreferredSize(getSize());
        getSwingComponent().setSize(getSize());
    }

    public UXLabel(String text) throws InvalidHexCode {
        this(text, new UXLabelSettings());
    }

    public UXLabel() throws InvalidHexCode {
        this("...", new UXLabelSettings());
    }

    public final void setText(String text) {
        this.text = text;
        label.setText(text);
    }

    public final void setColor(ThemeColor color, int duration) {
        animationColor.moveTo(color, duration);
    }

    public final void setColor(ThemeColor color) {
        setColor(color, 0);
    }

    public final void setFontSize(int size) {
        getSwingComponent().setFont(getSwingComponent().getFont().deriveFont((float) size));
    }

    public final int getFontSize() {
        return getSwingComponent().getFont().getSize();
    }

    public final int getWidth() {
        return getSwingComponent().getFontMetrics(getSwingComponent().getFont()).stringWidth(text);
    }

    public final int getHeight() {
        return getSwingComponent().getFontMetrics(getSwingComponent().getFont()).getHeight();
    }

    public final Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }
}
