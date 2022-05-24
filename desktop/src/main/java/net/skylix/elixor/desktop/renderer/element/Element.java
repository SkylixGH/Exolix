package net.skylix.elixor.desktop.renderer.element;

import net.skylix.elixor.desktop.renderer.gpu.Graphics;

import java.awt.geom.Path2D;

/**
 * A very basic structure for an element, this enforces some basic universal methods such as getters and setters for the
 * scale, color, and other related properties.
 */
public abstract class Element {
    /**
     * The render method for the element. This method will draw all base shape of the element,
     * children of this element will be rendered, by, and only by the core rendering engine based on this element's
     * width, height, borders, margins, paddings, and other related properties.
     * 
     * @param g The graphics object to render to.
     * @return The shape of the rendered element.
     */
    public abstract Path2D.Float render(Graphics graphics);
}
