package net.skylix.elixor.desktop.elements;

/**
 * An event instance for the div mouse event.
 */
public class DivMouseEvent {
    private final int mouseX;
    private final int mouseY;
    private final boolean isMouseDown;
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
}
