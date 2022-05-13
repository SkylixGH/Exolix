package net.skylix.elixor.desktop.elements;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.unit.Padding;
import net.skylix.elixor.desktop.unit.UnitAdapter;
import java.awt.Dimension;
import java.awt.*;

/**
 * This element is a container used for holding other elements.
 * One of the default pre defined elements in this framework.
 */
public class Div {
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
     * Create a new div element.
     */
    public Div() {
        padding = new Padding(0);
        margin = new Margin(0);
        borderRadius = new BorderRadius(0);
        size = new Dimension(0, 0);

        container = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                render((Graphics2D) g);
                System.out.println("Rendering");
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
    }

    /**
     * Force everything to render again.
     */
    public void reRender() {
        container.setSize(size);
        container.setPreferredSize(size);
        container.setMaximumSize(size);
        container.setMinimumSize(size);

        // TODO: Customise
        final boolean borderBox = false;

        if (margin.getTotal() > 0) {
            container.setBorder(BorderFactory.createEmptyBorder((int) margin.getTop(), (int) margin.getLeft(), (int) margin.getBottom(), (int) margin.getRight()));

            Dimension sizeFinal = new Dimension(
                (int) (size.width + margin.getLeft() + margin.getRight()),
                (int) (size.height + margin.getTop() + margin.getBottom())
            );

            container.setSize(sizeFinal);
            container.setPreferredSize(sizeFinal);
        }

        container.repaint();
    }

    /**
     * Set the size of this element.
     * @param width The width of this element.
     * @param height The height of this element.
     */ 
    public void setSize(int width, int height) {
        size.setSize(width, height);
        reRender();
    }

    /**
     * Get the size of this element.
     * @return The size of this element.
     */
    public Dimension getSize() {
        Dimension sizePrecise = new Dimension(
            (int) (size.width - (margin.getLeft() + margin.getRight())),
            (int) (size.height - (margin.getTop() + margin.getBottom()))
        );

        return sizePrecise;
    }

    /**
     * Set the width of this element.
     * @param width The width of this element.
     */
    public void setWidth(int width) {
        size.setSize(width, size.height);
        reRender();
    }

    /**
     * Set the height of this element.
     * @param height The height of this element.
     */
    public void setHeight(int height) {
        size.setSize(size.width, height);
        reRender();
    }

    /**
     * Get the width
     * @return The width of this element.
     */
    public int getWidth() {
        return (int) (size.getWidth() - (margin.getLeft() + margin.getRight()));
    }

    /**
     * Get the height
     * @return The height of this element.
     */
    public int getHeight() {
        return (int) (size.getHeight() - (margin.getTop() + margin.getBottom()));
    }

    /**
     * Set the margin of this element.
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
     * @return The margin.
     */
    public Margin getMargin() {
        return margin;
    }

    /**
     * Get the padding.
     * @return The padding.
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Set the padding of this element.
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
     * @return The border radius.
     */
    public BorderRadius getBorderRadius() {
        return borderRadius;
    }

    /**
     * Set the border radius of this element.
     * @param borderRadius The border radius to set.
     */
    public void setBorderRadius(BorderRadius borderRadius) {
        this.borderRadius.setArchHeight(borderRadius.getArchHeight());
        this.borderRadius.setArchWidth(borderRadius.getArchWidth());

        reRender();
    }

    /**
     * Get all the element children.
     * @return The element children.
     */
    public ArrayList<Div> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * Get JAVAX Swing component.
     * @return The Swing component.
     */
    public JComponent getSwingComponent() {
        return container;
    }

    /**
     * Set the border stroke width.
     * @param borderStrokeWidth The border stroke width.
     */
    public void setBorderStrokeWidth(int borderStrokeWidth) {
        this.borderStrokeWidth = borderStrokeWidth;
    }

    /**
     * Get the border stroke width.
     * @return The border stroke width.
     */
    public int getBorderStrokeWidth() {
        return borderStrokeWidth;
    }

    /**
     * Render to a graphics panel.
     * @param g Graphics renderer.
     */
    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (borderStrokeWidth > 0) {
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(borderStrokeWidth));

            g.drawRoundRect(
                (int) (margin.getLeft() + borderStrokeWidth / 2),
                (int) (margin.getTop() + borderStrokeWidth / 2),
                (int) (size.width - (margin.getLeft() + margin.getRight()) - borderStrokeWidth),
                (int) (size.height - (margin.getTop() + margin.getBottom()) - borderStrokeWidth),
                (int) (borderRadius.getArchWidth() + borderStrokeWidth / 2),
                (int) (borderRadius.getArchHeight() + borderStrokeWidth / 2)
            );
        }

        container.paintComponents(g);
        g.dispose();
    }
}
