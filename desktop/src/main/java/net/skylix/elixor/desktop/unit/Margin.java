package net.skylix.elixor.desktop.unit;

/**
 * A class used for creating a margin.
 */
public class Margin extends Unit {
    /**
     * The top margin.
     */
    private float top;

    /**
     * The right margin.
     */
    private float right;

    /**
     * The bottom margin.
     */
    private float bottom;

    /**
     * The left margin.
     */
    private float left;

    /**
     * Set the margin individually for all directions.
     * @param top The top margin.
     * @param right The right margin.
     * @param bottom The bottom margin.
     * @param left The left margin.
     */
    public Margin(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Set the margin for the top, right and left, and bottom.
     * @param top The top margin.
     * @param rightAndLeft The right and left margin.
     * @param bottom The bottom margin.
     */
    public Margin(int top, int rightAndLeft, int bottom) {
        this.top = top;
        this.right = rightAndLeft;
        this.bottom = bottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the margin for the top/bottom, and right/left.
     * @param topAndBottom The top and bottom margin.
     * @param rightAndLeft The right and left margin.
     */
    public Margin(int topAndBottom, int rightAndLeft) {
        this.top = topAndBottom;
        this.right = rightAndLeft;
        this.bottom = topAndBottom;
        this.left = rightAndLeft;
    }

    /**
     * Set the margin for all of the side.
     * @param allSides The margin for all sides.
     */
    public Margin(int allSides) {
        this.top = allSides;
        this.right = allSides;
        this.bottom = allSides;
        this.left = allSides;
    }

    /**
     * Get the top margin.
     * @return The top margin.
     */
    public float getTop() {
        return top;
    }

    /**
     * Get the right margin.
     * @return The right margin.
     */
    public float getRight() {
        return right;
    }

    /**
     * Get the bottom margin.
     * @return The bottom margin.
     */
    public float getBottom() {
        return bottom;
    }

    /**
     * Get the left margin.
     * @return The left margin.
     */
    public float getLeft() {
        return left;
    }

    /**
     * Get the total margin.
     * @return The total margin.
     */
    public float getTotal() {
        return top + right + bottom + left;
    }

    /**
     * Set the top margin.
     */
    public void setTop(float top) {
        this.top = top;
        executeOnChange();
    }

    /**
     * Set the right margin.
     */
    public void setRight(float right) {
        this.right = right;
        executeOnChange();
    }

    /**
     * Set the bottom margin.
     */
    public void setBottom(float bottom) {
        this.bottom = bottom;
        executeOnChange();
    }

    /**
     * Set the left margin.
     */
    public void setLeft(float left) {
        this.left = left;
        executeOnChange();
    }
}
