package net.skylix.elixor.desktop.unit;

/**
 * A class used for creating a border radius.
 */
public class BorderRadius extends Unit {
    /**
     * The top border radius.
     */
    private float top;

    /**
     * The right border radius.
     */
    private float right;

    /**
     * The bottom border radius.
     */
    private float bottom;

    /**
     * The left border radius.
     */
    private float left;

    /**
     * Set the border radius individually for all directions.
     * @param top The top border radius.
     * @param right The right border radius.
     * @param bottom The bottom border radius.
     * @param left The left border radius.
     */
    public BorderRadius(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Set the border radius for the top, right and left, and bottom.
     * @param top The top border radius.
     * @param rightAndLeft The right and left border radius.
     * @param bottom The bottom border radius.
     */
    public BorderRadius(int top, int rightAndLeft, int bottom) {
        this.top = top;
        this.right = rightAndLeft;
        this.bottom = bottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the border radius for the top/bottom, and right/left.
     * @param topAndBottom The top and bottom border radius.
     * @param rightAndLeft The right and left border radius.
     */
    public BorderRadius(int topAndBottom, int rightAndLeft) {
        this.top = topAndBottom;
        this.right = rightAndLeft;
        this.bottom = topAndBottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the border radius for all of the side.
     * @param allSides The border radius for all sides.
     */
    public BorderRadius(int allSides) {
        this.top = allSides;
        this.right = allSides;
        this.bottom = allSides;
        this.left = allSides;
    }

    /**
     * Get the top border radius.
     * @return The top border radius.
     */
    public float getTop() {
        return top;
    }

    /**
     * Get the right border radius.
     * @return The right border radius.
     */
    public float getRight() {
        return right;
    }

    /**
     * Get the bottom border radius.
     * @return The bottom border radius.
     */
    public float getBottom() {
        return bottom;
    }

    /**
     * Get the left border radius.
     * @return The left border radius.
     */
    public float getLeft() {
        return left;
    }

    /**
     * Get the total border radius.
     * @return The total border radius.
     */
    public float getTotal() {
        return top + right + bottom + left;
    }

    /**
     * Set the top border radius.
     */
    public void setTop(float top) {
        this.top = top;
        executeOnChange();
    }

    /**
     * Set the right border radius.
     */
    public void setRight(float right) {
        this.right = right;
        executeOnChange();
    }

    /**
     * Set the bottom border radius.
     */
    public void setBottom(float bottom) {
        this.bottom = bottom;
        executeOnChange();
    }

    /**
     * Set the left border radius.
     */
    public void setLeft(float left) {
        this.left = left;
        executeOnChange();
    }
}
