package net.skylix.elixor.desktop.ux.uxPanel;

import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UXPanel extends UXComponent {
    private final UXPanelSettings settings;
    private ThemeColor currentColor;

    public UXPanel(UXPanelSettings settings) throws InvalidHexCode {
        super(settings.theme, settings.accessibility);

        this.currentColor = settings.color;
        this.settings = settings;

        setElement(new Element(this));
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

    public Dimension getSize() {
        return getSwingComponent().getSize();
    }

    public void setColor(ThemeColor color) {
        currentColor = color;
        getSwingComponent().repaint();
    }

    public void setWidth(Integer width) {
        int finalWidth = 0;
        final Component[] components = getSwingComponent().getComponents();

        if (width == null) {
            for (Component component : components) {
                finalWidth += component.getWidth();
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

        if (height == null) {
            for (Component component : components) {
                finalHeight += component.getHeight();
            }
        } else {
            finalHeight = height < 0 ? 0 : height;
        }

        getSwingComponent().setSize(getWidth(), finalHeight);
        getSwingComponent().setPreferredSize(new Dimension(getWidth(), finalHeight));
    }

    public void setSize(Integer width, Integer height) {
        setWidth(width);
        setHeight(height);
    }

    public void add(UXComponent component) {
        getSwingComponent().add(component.getSwingComponent());

        recalculateMetrics();
        getSwingComponent().repaint();
    }

    private void recalculateMetrics() {
        setWidth(settings.width);
        setHeight(settings.height);
    }

    private class Element extends JPanel {
        public Element(UXPanel panel) {
            super();

            setAlignmentX(Component.CENTER_ALIGNMENT);
            setAlignmentY(Component.CENTER_ALIGNMENT);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    settings.onMouseClick.accept(panel);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    settings.onMouseDown.accept(panel);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    settings.onMouseUp.accept(panel);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    settings.onMouseEnter.accept(panel);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    settings.onMouseExit.accept(panel);
                }
            });

            setOpaque(false);
            setPreferredSize(getSize());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(currentColor.getAwtColor());
            g2d.fillRect(0, 0, getWidth(), getHeight());

            for (Component component : getComponents()) {
                component.paint(g);
            }

            g2d.dispose();
        }
    }
}
