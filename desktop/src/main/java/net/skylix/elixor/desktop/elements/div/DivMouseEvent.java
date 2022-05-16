package net.skylix.elixor.desktop.elements.div;

import net.skylix.elixor.desktop.unit.Position;

/**
 * An event instance for the div mouse event.
 *
 * @param mouseX      The mouse x position.
 * @param mouseY      The mouse y position.
 * @param isMouseOver Is the mouse over the element.
 * @param isMouseDown Is the mouse down.
 */
public record DivMouseEvent(int mouseX, int mouseY, boolean isMouseOver, boolean isMouseDown) {
    /**
     * Check to see if the mouse is over the element.
     *
     * @return If the mouse is over the element.
     */
    @Override
    public boolean isMouseOver() {
        return isMouseOver;
    }

    /**
     * Check to see if the mouse is pressed.
     *
     * @return If the mouse is pressed.
     */
    @Override
    public boolean isMouseDown() {
        return isMouseDown;
    }

    /**
     * Get the X position of the mouse.
     *
     * @return The X position of the mouse.
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Get the Y position of the mouse.
     *
     * @return The Y position of the mouse.
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Get the position of the mouse.
     *
     * @return The position of the mouse.
     */
    public Position getPosition() {
        return new Position(mouseX, mouseY);
    }
}
