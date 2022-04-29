package net.skylix.elixor.desktop.ux.uxPanel;

import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;

public class UXPanel extends UXComponent {
    private final UXPanelSettings settings;

    public UXPanel(UXPanelSettings settings) throws InvalidHexCode {
        super(settings.theme, settings.accessibility);

        this.settings = settings;

        Element el = new Element();
        setElement(el);

        setWidth(settings.width);
        setHeight(settings.height);
    }

    public UXPanel() throws InvalidHexCode {
        this(new UXPanelSettings());
    }

    public int getHeight() {
        return getSwingComponent().getHeight();
    }

    public int getWidth() {
        return getSwingComponent().getWidth();
    }

    public void setWidth(Integer width) {
        int finalWidth = 0;
        final Component[] components = getSwingComponent().getComponents();

        if (settings.width == null) {
            for (Component component : components) {
                width += component.getWidth();
            }
        } else {
            finalWidth = width < 0 ? 0 : width;
        }

        getSwingComponent().setSize(finalWidth, getHeight());
        getSwingComponent().setPreferredSize(new Dimension(finalWidth, getHeight()));
    }

    public void setHeight(Integer height) {
        int finalHeight = 0;
        final Component[] components = getSwingComponent().getComponents();

        if (settings.height == null) {
            for (Component component : components) {
                height += component.getHeight();
            }
        } else {
            finalHeight = height < 0 ? 0 : height;
        }

        getSwingComponent().setSize(getWidth(), finalHeight);
        getSwingComponent().setPreferredSize(new Dimension(getWidth(), finalHeight));
    }

    public void add(UXComponent component) {
        getSwingComponent().add(component.getSwingComponent());
    }

    private class Element extends JPanel {
        public Element() {
            super();

            int width = 0;
            int height = 0;

            setPreferredSize(new Dimension(width, height));
            setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.dispose();
        }
    }
}
