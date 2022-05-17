package net.skylix.elixor.desktop.element;

import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

/**
 * A component that has some basics required properties.
 */
public abstract class Component extends Element {
    /**
     * The element render method.
     *
     * @param g2d The graphics output.
     * @param window The window.
     * @param parent The parent element.
     */
    public abstract void render(Graphics2D g2d, Window window, Component parent);

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
    public abstract Size getMinimumSize();

    /**
     * Get the maximum size.
     *
     * @return The maximum size.
     */
    public abstract Size getMaximumSize();

    /**
     * Get minimum width.
     *
     * @return The minimum width.
     */
    public int getMinimumWidth() {
        return getMinimumSize().getWidth();
    }

    /**
     * Get minimum height.
     *
     * @return The minimum height.
     */
    public int getMinimumHeight() {
        return getMinimumSize().getHeight();
    }

    /**
     * Get maximum width.
     *
     * @return The maximum width.
     */
    public int getMaximumWidth() {
        return getMaximumSize().getWidth();
    }

    /**
     * Get maximum height.
     *
     * @return The maximum height.
     */
    public int getMaximumHeight() {
        return getMaximumSize().getHeight();
    }

    /**
     * Get the parent component.
     *
     * @return The parent component.
     */
    public abstract Component getParent();

    /**
     * Get the window.
     *
     * @return The window.
     */
    public abstract Window getWindow();
}
