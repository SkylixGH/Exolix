package net.skylix.elixor.desktop.unit;

/**
 * A class used for creating a border radius.
 */
public class BorderRadius extends Unit {
    /**
     * The top left radius.
     */
    private float topLeft;

    /**
     * The top right radius.
     */
    private float topRight;

    /**
     * The bottom left border radius.
     */
    private float bottomLeft;

    /**
     * The bottom right border radius.
     */
    private float bottomRight;

    /**
     * Create a new border radius.
     * @param topLeft The top left radius.
     * @param topRight The top right radius.
     * @param bottomLeft The bottom left radius.
     * @param bottomRight The bottom right radius.
     */
    public BorderRadius(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Create a new border radius.
     * @param radius The radius for all sides.
     */
    public BorderRadius(float radius) {
        this(radius, radius, radius, radius);
    }

    /**
     * Create a new border radius.
     * @Param topLeftRIght The radius for the top left and right.
     * @Param bottomLeftRight The radius for the bottom left and right.
     */
    public BorderRadius(float topLeftRight, float bottomLeftRight) {
        this(topLeftRight, topLeftRight, bottomLeftRight, bottomLeftRight);
    }

    /**
     * Get the top left radius.
     * @return The top left radius.
     */
    public float getTopLeft() {
        return topLeft;
    }

    /**
     * Set the top left radius.
     * @param topLeft The top left radius.
     */
    public void setTopLeft(float topLeft) {
        this.topLeft = topLeft;
        executeOnChange();
    }

    /**
     * Get the top right radius.
     * @return The top right radius.
     */
    public float getTopRight() {
        return topRight;
    }

    /**
     * Set the top right radius.
     * @param topRight The top right radius.
     */
    public void setTopRight(float topRight) {
        this.topRight = topRight;
        executeOnChange();
    }

    /**
     * Get the bottom left radius.
     * @return The bottom left radius.
     */
    public float getBottomLeft() {
        return bottomLeft;
    }

    /**
     * Set the bottom left radius.
     * @param bottomLeft The bottom left radius.
     */
    public void setBottomLeft(float bottomLeft) {
        this.bottomLeft = bottomLeft;
        executeOnChange();
    }

    /**
     * Get the bottom right radius.
     * @return The bottom right radius.
     */
    public float getBottomRight() {
        return bottomRight;
    }

    /**
     * Set the bottom right radius.
     * @param bottomRight The bottom right radius.
     */
    public void setBottomRight(float bottomRight) {
        this.bottomRight = bottomRight;
        executeOnChange();
    }

    /**
     * Get the radius for all sides.
     * @return The radius for all sides.
     */
    public float getTotal() {
        return topLeft;
    }
}
