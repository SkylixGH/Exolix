package net.skylix.elixor.desktop.window;

/**
 * A window mouse event for the window adapter.
 *
 * @param x      The x coordinate of the mouse.
 * @param y      The y coordinate of the mouse.
 * @param button The button that was pressed.
 */
public record WindowMouseEvent(int x, int y, int button) {
    /**
     * Get the x coordinate of the mouse.
     *
     * @return The x coordinate of the mouse.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate of the mouse.
     *
     * @return The y coordinate of the mouse.
     */
    public int getY() {
        return y;
    }

    /**
     * Get the button that was pressed.
     *
     * @return The button that was pressed.
     */
    public int getButton() {
        return button;
    }
}
