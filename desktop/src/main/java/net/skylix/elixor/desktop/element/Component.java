package net.skylix.elixor.desktop.element;

import net.skylix.elixor.desktop.unit.Size;

import java.awt.*;

/**
 * A component that has some basics required properties.
 */
public abstract class Component extends Element {
    /**
     * The element render method.
     *
     * @param g2d The graphics output.
     */
    public abstract void render(Graphics2D g2d);

    /**
     * Get the width.
     *
     * @return The width.
     */
    public abstract int getWidth();

    /**
     * Get the height.
     *
     * @return The height.
     */
    public abstract int getHeight();

    /**
     * Get the size.
     *
     * @return The size.
     */
    public Size getSize() {
        return new Size(getWidth(), getHeight());
    }

    /**
     * Get the minimum size.
     *
     * @return The minimum size.
     */
    public Size getMinSize() {
        return new Size(0, 0);
    }

    /**
     * Get the maximum size.
     *
     * @return The maximum size.
     */
    public Size getMaxSize() {
        return new Size(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Get the minimum width.
     *
     * @return The minimum width.
     */
    public int getMinWidth() {
        return getMinSize().getWidth();
    }

    /**
     * Get the minimum height.
     *
     * @return The minimum height.
     */
    public int getMinHeight() {
        return getMinSize().getHeight();
    }

    /**
     * Get the maximum width.
     *
     * @return The maximum width.
     */
    public int getMaxWidth() {
        return getMaxSize().getWidth();
    }

    /**
     * Get the maximum height.
     *
     * @return The maximum height.
     */
    public int getMaxHeight() {
        return getMaxSize().getHeight();
    }
}
