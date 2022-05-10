package net.skylix.elixor.desktop.unit;

/**
 * A class used for creating a padding.
 */
public class Padding extends Unit {
    /**
     * The top padding.
     */
    private float top;

    /**
     * The right padding.
     */
    private float right;

    /**
     * The bottom padding.
     */
    private float bottom;

    /**
     * The left padding.
     */
    private float left;

    /**
     * Set the padding individually for all directions.
     * @param top The top padding.
     * @param right The right padding.
     * @param bottom The bottom padding.
     * @param left The left padding.
     */
    public Padding(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Set the padding for the top, right and left, and bottom.
     * @param top The top padding.
     * @param rightAndLeft The right and left padding.
     * @param bottom The bottom padding.
     */
    public Padding(int top, int rightAndLeft, int bottom) {
        this.top = top;
        this.right = rightAndLeft;
        this.bottom = bottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the padding for the top/bottom, and right/left.
     * @param topAndBottom The top and bottom padding.
     * @param rightAndLeft The right and left padding.
     */
    public Padding(int topAndBottom, int rightAndLeft) {
        this.top = topAndBottom;
        this.right = rightAndLeft;
        this.bottom = topAndBottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the padding for all of the side.
     * @param allSides The padding for all sides.
     */
    public Padding(int allSides) {
        this.top = allSides;
        this.right = allSides;
        this.bottom = allSides;
        this.left = allSides;
    }

    /**
     * Get the top padding.
     * @return The top padding.
     */
    public float getTop() {
        return top;
    }

    /**
     * Get the right padding.
     * @return The right padding.
     */
    public float getRight() {
        return right;
    }

    /**
     * Get the bottom padding.
     * @return The bottom padding.
     */
    public float getBottom() {
        return bottom;
    }

    /**
     * Get the left padding.
     * @return The left padding.
     */
    public float getLeft() {
        return left;
    }

    /**
     * Get the total padding.
     * @return The total padding.
     */
    public float getTotal() {
        return top + right + bottom + left;
    }

    /**
     * Set the top padding.
     */
    public void setTop(float top) {
        this.top = top;
        executeOnChange();
    }

    /**
     * Set the right padding.
     */
    public void setRight(float right) {
        this.right = right;
        executeOnChange();
    }

    /**
     * Set the bottom padding.
     */
    public void setBottom(float bottom) {
        this.bottom = bottom;
        executeOnChange();
    }

    /**
     * Set the left padding.
     */
    public void setLeft(float left) {
        this.left = left;
        executeOnChange();
    }
}
