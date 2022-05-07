package net.skylix.elixor.desktop.ux.uxPanel;

import net.skylix.elixor.desktop.animation.AnimationColor;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.local.windows.WindowsJFrameProcess;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.util.stream.Stream;

public class UXPanel extends UXComponent {
    private final UXPanelSettings settings;
    private ThemeColor currentColor;
    private Integer currentWidth;
    private Integer currentHeight;
    private UXPanelFlowDirection currentFlowDirection;
    private UXPanelRowAlignment currentRowAlignment;
    private UXPanelColumnAlignment currentColumnAlignment;
    private final AnimationColor animationColor;
    private final Point[] regionOnWindow;
    private ModJFrame frame;

    public UXPanel(UXPanelSettings settings) throws InvalidHexCode {
        super(settings.theme, settings.accessibility);

        this.settings = settings;

        this.regionOnWindow = new Point[] {
            new Point(0, 0),
            new Point(0, 0)
        };

        animationColor = new AnimationColor(settings.color, (ac, color) -> {
            JComponent swingComponent = getSwingComponent();
            this.currentColor = color;

            if (swingComponent != null) {
                swingComponent.setBackground(color.getAwtColor());
                swingComponent.repaint();
            }
        });

        currentHeight = settings.height;
        currentWidth = settings.width;
        currentColumnAlignment = settings.columnAlignment;
        currentRowAlignment = settings.rowAlignment;
        currentFlowDirection = settings.flowDirection;
        currentColor = settings.color;

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

    public final void setRowAlignment(UXPanelRowAlignment rowAlignment) {
        currentRowAlignment = rowAlignment;
        getSwingComponent().repaint();
    }

    public final void setColumnAlignment(UXPanelColumnAlignment columnAlignment) {
        currentColumnAlignment = columnAlignment;
        getSwingComponent().repaint();
    }

    public final void setFlowDirection(UXPanelFlowDirection flowDirection) {
        currentFlowDirection = flowDirection;
        getSwingComponent().repaint();
    }

    public void setColor(ThemeColor color, int duration) {
        animationColor.moveTo(color,  duration);
    }

    public void setColor(ThemeColor color) {
        setColor(color, 0);
    }

    public void setWidth(Integer width) {
        int finalWidth = 0;
        final Component[] components = getSwingComponent().getComponents();

        if (width == null) {
            for (Component component : components) {
                finalWidth += component.getPreferredSize().width + 10;
            }
        } else {
            finalWidth = width < 0 ? 0 : width;
        }

        currentWidth = finalWidth;

        getSwingComponent().setSize(finalWidth, getHeight());
        getSwingComponent().setPreferredSize(new Dimension(finalWidth, getHeight()));
    }

    public void setHeight(Integer height) {
        int finalHeight = 0;
        final Component[] components = getSwingComponent().getComponents();

        if (height == null) {
            int largestHeight = 0;

            for (Component component : components) {
                if (component.getPreferredSize().height > largestHeight) {
                    largestHeight = component.getPreferredSize().height;
                }
            }

            finalHeight = largestHeight + 10;
        } else {
            finalHeight = height < 0 ? 0 : height;
        }

        currentHeight = finalHeight;

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
        setWidth(currentWidth);
        setHeight(currentHeight);
    }

    private class Element extends JPanel {
        public Element(UXPanel panel) {
            super();

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

        private Component findTallestComponent(Component[] components) {
            Component tallest = components[0];

            for (Component component : components) {
                if (component.getPreferredSize().height > tallest.getPreferredSize().height) {
                    tallest = component;
                }
            }

            return tallest;
        }

        private int getTotalWidth(Component[] components) {
            int totalWidth = 0;

            for (Component component : components) {
                totalWidth += component.getPreferredSize().width;
            }

            return totalWidth;
        }

        private void handleDraggingSupport() {
            WindowsJFrameProcess winProcess = frame.getWinProcess();

            // get all parents and grandparents
            Component[] parent = new Component[] {};

            // recursively get all parents with lambda
            Stream.iterate(getParent(), c -> c.getParent());

            final Point startRegion = new Point(getX(), getY());
            final Point endRegion = new Point(getX() + getWidth(), getY() + getHeight());

            // add top pixels of all parents to startRegion and end region
            Stream.of(parent).forEach(c -> {
                startRegion.y += c.getY();
                endRegion.y += c.getY();
            });

            // add left pixels of all parents to startRegion and end region
            Stream.of(parent).forEach(c -> {
                startRegion.x += c.getX();
                endRegion.x += c.getX();
            });

            System.out.println("startRegion: " + startRegion);
            System.out.println("endRegion: " + endRegion);
            // log width and height
            System.out.println("width: " + getWidth());
            System.out.println("height: " + getHeight());

            if (winProcess != null && settings.allowWindowDrag) {
                // Add the window borders and offsets
                startRegion.x += 0;
                startRegion.y += 20;
                endRegion.x += 0;
                endRegion.y += 20;

                regionOnWindow[0] = startRegion;
                regionOnWindow[1] = endRegion;

                winProcess.removeTitleBarDragRegion(regionOnWindow);
                winProcess.addTitleBarDragRegion(regionOnWindow);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (frame == null) {
                frame = (ModJFrame) SwingUtilities.getWindowAncestor(this);
            }

            handleDraggingSupport();
            recalculateMetrics();
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(currentColor.getAwtColor());

            if (settings.cornerRadius > 0) {
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), settings.cornerRadius, settings.cornerRadius);
            } else {
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }

            int spacingX = 10;
            int spacingY = 10;
            boolean wrapping = false;

            int componentID = 0;
            int lastX = 0;
            int lastY = 0;
            int lastRenderingWidth = 0;
            int lastRenderingHeight = 0;
            int totalLinesWrapped = 0;

            for (Component component : getComponents()) {
                if (!wrapping) {
                    if (currentFlowDirection == UXPanelFlowDirection.ROW) {
                        if (componentID == 0 && currentRowAlignment == UXPanelRowAlignment.RIGHT) {
                            lastX = getWidth() - component.getWidth();
                        } else if (componentID == 0 && currentRowAlignment == UXPanelRowAlignment.CENTER) {
                            final int totalWidth = getTotalWidth(getComponents());
                            lastX = (getWidth() - totalWidth) / 2;
                        }

                        if (componentID == 0 && currentColumnAlignment == UXPanelColumnAlignment.CENTER) {
                            Component tallestComponent = findTallestComponent(getComponents());
                            lastY = (getHeight() - tallestComponent.getHeight()) / 2;
                        } else if (componentID == 0 && currentColumnAlignment == UXPanelColumnAlignment.BOTTOM) {
                            Component tallestComponent = findTallestComponent(getComponents());
                            lastY = getHeight() - tallestComponent.getHeight();
                        }

                        component.setLocation(lastX, lastY);

                        if (currentRowAlignment == UXPanelRowAlignment.LEFT) {
                            if (componentID == getComponents().length - 1) lastX += component.getWidth();
                            else lastX += component.getWidth() + spacingX;
                        } else if (currentRowAlignment == UXPanelRowAlignment.CENTER) {
                            if (componentID == getComponents().length - 1) lastX += component.getWidth();
                            else lastX += component.getWidth() + spacingX;
                        } else if (currentRowAlignment == UXPanelRowAlignment.RIGHT) {
                            if (componentID == getComponents().length - 1) lastX -= component.getWidth();
                            else lastX -= component.getWidth() + spacingX;
                        }
                    } else {

                    }
                }

                componentID++;
            }

            paintChildren(g);
            g2d.dispose();
        }
    }
}
