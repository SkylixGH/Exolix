package net.skylix.elixor.desktop.unit;

/**
 * A position instance.
 */
public class Position extends Unit {
    /**
     * The X position.
     */
    private int x;

    /**
     * The Y position.
     */
    private int y;

    /**
     * Create a new position instance.
     * 
     * @param x The x position.
     * @param y The y position.
     */
    public Position(int x, int y) {
        super();

        this.x = x;
        this.y = y;
    }

    /**
     * Create a new position instance.
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Get the X position.
     * 
     * @return The X position.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y position.
     * 
     * @return The Y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the X position.
     * 
     * @param x The X position.
     */
    public void setX(int x) {
        this.x = x;
        executeOnChange();
    }

    /**
     * Set the Y position.
     * 
     * @param y The Y position.
     */
    public void setY(int y) {
        this.y = y;
        executeOnChange();
    }

    /**
     * Get the total position.
     * 
     * @return The total position.
     */
    public int getTotal() {
        return x + y;
    }
}
