package net.skylix.elixor.desktop.elements;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.unit.Padding;
import net.skylix.elixor.desktop.unit.UnitAdapter;
import java.awt.Dimension;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Area;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Path2D;

/**
 * This element is a container used for holding other elements.
 * One of the default pre defined elements in this framework.
 */
public class Div extends DivAdapter {
    /**
     * The element padding.
     */
    private final Padding padding;

    /**
     * The Swing container
     */
    private final JPanel container;

    /**
     * The element margin.
     */
    private final Margin margin;

    /**
     * The element border radius.
     */
    private final BorderRadius borderRadius;

    /**
     * All the element children.
     */
    private final ArrayList<Div> nodes = new ArrayList<>();

    /**
     * The border stroke width.
     */
    private int borderStrokeWidth = 1;

    /**
     * The size of this element.
     */
    private final Dimension size;

    /**
     * The graphics panel rectangle.
     */
    private Path2D.Float rect;

    /**
     * Is the mouse over.
     */
    private boolean mouseOver = false;

    /**
     * Create a new div element.
     */
    public Div() {
        super();

        addListener(this);

        padding = new Padding(0);
        margin = new Margin(0);
        borderRadius = new BorderRadius(0);
        size = new Dimension(0, 0);

        container = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                handleMetrics();
                render((Graphics2D) g);
            }
        };

        padding.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                reRender();
            }
        });

        margin.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                reRender();
            }
        });

        borderRadius.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                reRender();
            }
        });

        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                handleMouseEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                handleMouseEvent(e);
            }
        });
    }

    /**
     * Handle the mouse event.
     * 
     * @param event Mouse event.
     */
    private void handleMouseEvent(MouseEvent event) {
        if (listeners.size() == 0)
            return;

        DivMouseEvent eventOut = new DivMouseEvent(event.getX(), event.getY(), false, false);

        executeOnMouseEvent(eventOut);
    }

    /**
     * Force everything to render again.
     */
    public void reRender() {
        handleMetrics();
        container.repaint();
    }

    /**
     * Handle the component metrics.
     */
    private void handleMetrics() {
        container.setSize(size);
        container.setPreferredSize(size);

        // TODO: Customise
        final boolean borderBox = false;

        if (margin.getTotal() > 0) {
            container.setBorder(BorderFactory.createEmptyBorder((int) margin.getTop(), (int) margin.getLeft(),
                    (int) margin.getBottom(), (int) margin.getRight()));

            Dimension sizeFinal = new Dimension(
                    (int) (size.width + margin.getLeft() + margin.getRight()),
                    (int) (size.height + margin.getTop() + margin.getBottom()));

            container.setSize(sizeFinal);
            container.setPreferredSize(sizeFinal);
        }
    }

    /**
     * Set the size of this element.
     * 
     * @param width  The width of this element.
     * @param height The height of this element.
     */
    public void setSize(int width, int height) {
        size.setSize(width, height);
        reRender();
    }

    /**
     * Get the size of this element.
     * 
     * @return The size of this element.
     */
    public Dimension getSize() {
        Dimension sizePrecise = new Dimension(
                (int) (size.width - (margin.getLeft() + margin.getRight())),
                (int) (size.height - (margin.getTop() + margin.getBottom())));

        return sizePrecise;
    }

    /**
     * Set the width of this element.
     * 
     * @param width The width of this element.
     */
    public void setWidth(int width) {
        size.setSize(width, size.height);
        reRender();
    }

    /**
     * Set the height of this element.
     * 
     * @param height The height of this element.
     */
    public void setHeight(int height) {
        size.setSize(size.width, height);
        reRender();
    }

    /**
     * Get the width
     * 
     * @return The width of this element.
     */
    public int getWidth() {
        return (int) (size.getWidth() - (margin.getLeft() + margin.getRight()));
    }

    /**
     * Get the height
     * 
     * @return The height of this element.
     */
    public int getHeight() {
        return (int) (size.getHeight() - (margin.getTop() + margin.getBottom()));
    }

    /**
     * Set the margin of this element.
     * 
     * @param margin The margin to set.
     */
    public void setMargin(Margin margin) {
        this.margin.setTop(margin.getTop());
        this.margin.setBottom(margin.getBottom());
        this.margin.setLeft(margin.getLeft());
        this.margin.setRight(margin.getRight());

        reRender();
    }

    /**
     * Get the margin.
     * 
     * @return The margin.
     */
    public Margin getMargin() {
        return margin;
    }

    /**
     * Get the padding.
     * 
     * @return The padding.
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Set the padding of this element.
     * 
     * @param padding The padding to set.
     */
    public void setPadding(Padding padding) {
        this.padding.setTop(padding.getTop());
        this.padding.setBottom(padding.getBottom());
        this.padding.setLeft(padding.getLeft());
        this.padding.setRight(padding.getRight());

        reRender();
    }

    /**
     * Get the border radius.
     * 
     * @return The border radius.
     */
    public BorderRadius getBorderRadius() {
        return borderRadius;
    }

    /**
     * Set the border radius of this element.
     * 
     * @param borderRadius The border radius to set.
     */
    public void setBorderRadius(BorderRadius borderRadius) {
        this.borderRadius.setTopLeft(borderRadius.getTopLeft());
        this.borderRadius.setTopRight(borderRadius.getTopRight());
        this.borderRadius.setBottomLeft(borderRadius.getBottomLeft());
        this.borderRadius.setBottomRight(borderRadius.getBottomRight());

        reRender();
    }

    /**
     * Get all the element children.
     * 
     * @return The element children.
     */
    public ArrayList<Div> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * Get JAVAX Swing component.
     * 
     * @return The Swing component.
     */
    public JComponent getSwingComponent() {
        return container;
    }

    /**
     * Set the border stroke width.
     * 
     * @param borderStrokeWidth The border stroke width.
     */
    public void setBorderStrokeWidth(int borderStrokeWidth) {
        this.borderStrokeWidth = borderStrokeWidth;
        reRender();
    }

    /**
     * Get the border stroke width.
     * 
     * @return The border stroke width.
     */
    public int getBorderStrokeWidth() {
        return borderStrokeWidth;
    }

    /**
     * Check to see if the mouse is over this element.
     * 
     * @return True if the mouse is over this element.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * Render to a graphics panel.
     * 
     * @param g Graphics renderer.
     */
    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        container.setOpaque(false);

        final int width = getWidth() - 16;
        final int height = getHeight() - 39;

        final int topLeftRadius = (int) borderRadius.getTopLeft();
        final int topRightRadius = (int) borderRadius.getTopRight();
        final int bottomLeftRadius = (int) borderRadius.getBottomLeft();
        final int bottomRightRadius = (int) borderRadius.getBottomRight();

        // set clip region
        g.setClip(0, 0, width, height);

        rect = new RoundRectangle(
            width, 
            height,
            (borderStrokeWidth / 2),
            (borderStrokeWidth / 2),
            topLeftRadius,
            topRightRadius,
            bottomLeftRadius,
            bottomRightRadius,
            borderStrokeWidth
        );

        Area area = new Area(rect);

        final int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        final int mouseY = MouseInfo.getPointerInfo().getLocation().y;

        if (area.contains(mouseX, mouseY)) {
            mouseOver = true;
        } else {
            mouseOver = false;
        }

        if (borderStrokeWidth > 0) {
            g.setColor(Color.BLACK);
            // Add a stroke that does not expand the bounds, make the stroke radius 4px
            g.setStroke(new BasicStroke(borderStrokeWidth));
        }

        if (borderStrokeWidth > 0)
            g.draw(rect);

        g.setColor(Color.pink);
        g.fill(rect);

        container.paintComponents(g);
        g.dispose();
    }

    /**
     * On mouse event.
     * 
     * @param event The mouse event.
     */
    @Override
    public void onMouseEvent(DivMouseEvent event) {

    }
}

/**
 * A round rectangle shape
 */
class RoundRectangle extends Path2D.Float {
    /**
     * Create the round rectangle
     *
     * @param widthRaw The width of the rectangle
     * @param heightRaw The height of the rectangle
     * @param x The x position of the rectangle
     * @param y The y position of the rectangle
     * @param topLeftRadius The radius of the top left corner
     * @param topRightRadius The radius of the top right corner
     * @param bottomLeftRadius The radius of the bottom left corner
     * @param bottomRightRadius The radius of the bottom right corner
     * @param strokeWidth The stroke width
     */
    public RoundRectangle(
            int widthRaw, int heightRaw, int x, int y, int topLeftRadius,
            int topRightRadius, int bottomLeftRadius, int bottomRightRadius,
            int strokeWidth
    ) {
        super();

        int width = widthRaw - strokeWidth;
        int height = heightRaw - strokeWidth;

        // render a different way if the curveTo issues happen
        if (topLeftRadius > width / 2 || topLeftRadius > height / 2) {
            topLeftRadius = width / 2;
        }

        if (topRightRadius > width / 2 || topRightRadius > height / 2) {
            topRightRadius = width / 2;
        }

        if (bottomLeftRadius > width / 2 || bottomLeftRadius > height / 2) {
            bottomLeftRadius = width / 2;
        }

        if (bottomRightRadius > width / 2 || bottomRightRadius > height / 2) {
            bottomRightRadius = width / 2;
        }

        if (topLeftRadius > height / 2 || topLeftRadius > width / 2) {
            topLeftRadius = height / 2;
        }

        if (topRightRadius > height / 2 || topRightRadius > width / 2) {
            topRightRadius = height / 2;
        }

        if (bottomLeftRadius > height / 2 || bottomLeftRadius > width / 2) {
            bottomLeftRadius = height / 2;
        }

        if (bottomRightRadius > height / 2 || bottomRightRadius > width / 2) {
            bottomRightRadius = height / 2;
        }

        moveTo(x + topLeftRadius, y);
        lineTo(x + width - topRightRadius, y);
        quadTo(x + width, y, x + width, y + topRightRadius);
        lineTo(x + width, y + height - bottomRightRadius);
        quadTo(x + width, y + height, x + width - bottomRightRadius, y + height);
        lineTo(x + bottomLeftRadius, y + height);
        quadTo(x, y + height, x, y + height - bottomLeftRadius);
        lineTo(x, y + topLeftRadius);
        quadTo(x, y, x + topLeftRadius, y);

        closePath();
    }
}
