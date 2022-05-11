package net.skylix.elixor.desktop.ux.uxLabel;

import net.skylix.elixor.desktop.accessibility.Accessibility;
import net.skylix.elixor.desktop.animation.AnimationColor;
import net.skylix.elixor.desktop.theme.Theme;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxPanel.UXPanelMargin;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXLabel extends UXComponent {
    private final UXLabelSettings settings;
    private final AnimationColor animationColor;
    private final JLabel label;
    private String text;
    private UXPanelMargin margin;

    public UXLabel(String text, UXLabelSettings settings) {
        super(settings.theme, settings.accessibility);

        this.settings = settings;
        this.text = text;
        this.label = new JLabel();
        this.margin = settings.margin;

        label.setText(text);
        label.setFont(new Font("Arial", Font.PLAIN, settings.fontSize));
        label.setForeground(settings.color.getAwtColor());

        animationColor = new AnimationColor(settings.color, (acl, color) -> {
            label.setForeground(color.getAwtColor());
        });

        LabelElement labelElement = new LabelElement(this);
        setElement(labelElement);

        getSwingComponent().setPreferredSize(getSize());
        getSwingComponent().setSize(getSize());

        getSwingComponent().repaint();
    }

    public final void setMargin(UXPanelMargin margin) {
        this.margin = margin;
        getSwingComponent().repaint();
    }

    private static class LabelElement extends JComponent {
        private final UXLabel uxLabel;

        public LabelElement(UXLabel uxLabel) {
            super();

            this.uxLabel = uxLabel;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(uxLabel.label.getForeground());
            g2d.drawString(uxLabel.label.getText(), 0 + uxLabel.margin.getLeft(), uxLabel.label.getFont().getSize() + uxLabel.margin.getTop());
        } 
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

        getSwingComponent().repaint();
    }

    public final void setColor(ThemeColor color, int duration) {
        animationColor.moveTo(color, duration);
    }

    public final void setColor(ThemeColor color) {
        setColor(color, 0);
    }

    public final void setFontSize(int size) {
        label.setFont(label.getFont().deriveFont((float) size));
        getSwingComponent().repaint();
    }

    public final int getFontSize() {
        return label.getFont().getSize();
    }

    public final int getWidth() {
        return label.getFontMetrics(label.getFont()).stringWidth(text) + margin.getLeft() + margin.getRight();
    }

    public final int getHeight() {
        return label.getFontMetrics(label.getFont()).getHeight() + margin.getTop() + margin.getBottom();
    }

    public final Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }
}
