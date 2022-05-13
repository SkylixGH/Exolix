package net.skylix.elixor.desktop.unit;

/**
 * A class used for creating a border radius.
 */
public class BorderRadius extends Unit {
    /**
     * The width radius.
     */
    private float width;

    /**
     * The height radius.
     */
    private float height;

    /**
     * Create a new border radius.
     * @param widthArch The top left radius.
     * @param heightArch The top right radius.
     */
    public BorderRadius(float widthArch, float heightArch) {
        width = widthArch;
        height = heightArch;
    }

    /**
     * Create a new border radius.
     * @param all The radius.
     */
    public BorderRadius(float all) {
        width = all;
        height = all;
    }

    /**
     * Get the arch width.
     * @return The arch width.
     */
    public float getArchWidth() {
        return width;
    }

    /**
     * Get the arch height.
     * @return The arch height.
     */
    public float getArchHeight() {
        return height;
    }

    /**
     * Set the arch width.
     * @param width The arch width.
     */
    public void setArchWidth(float width) {
        this.width = width;
        executeOnChange();
    }

    /**
     * Set the arch height.
     * @param height The arch height.
     */
    public void setArchHeight(float height) {
        this.height = height;
        executeOnChange();
    }

    /**
     * Get the total radius.
     * @return The total radius.
     */
    public float getTotal() {
        return width + height;
    }
}
