package net.skylix.elixor.desktop.unit;

/**
 * A unit to represent a size.
 */
public class Size extends Unit {
    /**
     * The width.
     */
    private int width;

    /**
     * The height.
     */
    private int height;

    /**
     * Create a new size unit instance.
     *
     * @param width The width.
     * @param height The height.
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Create a new size unit instance.
     */
    public Size() {
        this(0, 0);
    }

    /**
     * Get the width.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the width.
     *
     * @param width The width.
     */
    public void setWidth(int width) {
        this.width = width;
        executeOnChange();
    }

    /**
     * Set the height.
     *
     * @param height The height.
     */
    public void setHeight(int height) {
        this.height = height;
        executeOnChange();
    }
}
