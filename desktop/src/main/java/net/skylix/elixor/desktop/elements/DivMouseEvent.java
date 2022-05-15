package net.skylix.elixor.desktop.elements;

import net.skylix.elixor.desktop.unit.Position;

/**
 * An event instance for the div mouse event.
 */
public class DivMouseEvent {
    /**
     * The X position of the mouse.
     */
    private final int mouseX;

    /**
     * The Y position of the mouse.
     */
    private final int mouseY;

    /**
     * If the mouse is pressed.
     */
    private final boolean isMouseDown;

    /**
     * If the mouse is over the element.
     */
    private final boolean isMouseOver;
    
    /**
     * Create a new mouse event.
     * @param mouseX The mouse x position.
     * @param mouseY The mouse y position.
     * @param isMouseOver Is the mouse over the element.
     * @param isMouseDown Is the mouse down.
     */
    public DivMouseEvent(int mouseX, int mouseY, boolean isMouseOver, boolean isMouseDown) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isMouseOver = isMouseOver;
        this.isMouseDown = isMouseDown;
    }

    /**
     * Check to see if the mouse is over the element.
     * 
     * @return If the mouse is over the element.
     */
    public boolean isMouseOver() {
        return isMouseOver;
    }

    /**
     * Check to see if the mouse is pressed.
     * 
     * @return If the mouse is pressed.
     */
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
