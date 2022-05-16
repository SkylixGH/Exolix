package net.skylix.elixor.desktop.window;

/**
 * A window mouse event for the window adapter.
 *
 * @param x      The x coordinate of the mouse.
 * @param y      The y coordinate of the mouse.
 * @param button The button that was pressed.
 * @param mouseOver Is the mouse over the window.
 */
public record WindowMouseEvent(int x, int y, int button, boolean mouseOver) {
    /**
     * Get the x coordinate of the mouse.
     *
     * @return The x coordinate of the mouse.
     */
    public int getMouseX() {
        return x;
    }

    /**
     * Get the y coordinate of the mouse.
     *
     * @return The y coordinate of the mouse.
     */
    public int getMouseY() {
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

    /**
     * is the mouse over the window.
     *
     * @return If the mouse is over the window.
     */
    public boolean isMouseOver() {
        return mouseOver;
    }
}
