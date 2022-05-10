package net.skylix.elixor.desktop.elements;

import java.util.ArrayList;

import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.unit.Padding;
import net.skylix.elixor.desktop.unit.UnitAdapter;
import java.awt.Dimension;
import java.awt.*;
import java.awt.geom.Path2D;

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
        System.out.println("Rerendering...");
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
     * Get the border radius.
     * @return The border radius.
     */
    public BorderRadius getBorderRadius() {
        return borderRadius;
    }

    /**
     * Get all of the element children.
     * @return The element children.
     */
    public ArrayList<Div> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * Render to a graphics panel.
     * @param g Graphics renderer.
     */
    public void render(Graphics2D g) {
        Path2D.Float p = new Path2D.Float() {{
            moveTo(100, 100);

            lineTo(100 + 20, 100);
            curveTo(100 + 20, 100, 100 + 30, 10, 10, 100 + 10);
        }};

        g.draw(p);
    }
}
