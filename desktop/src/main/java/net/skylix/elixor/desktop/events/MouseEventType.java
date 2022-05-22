package net.skylix.elixor.desktop.events;

/**
 * The trigger for the mouse event.
 */
public enum MouseEventType {
    /**
     * The mouse has been moved.
     */
    MOUSE_MOVED,

    /**
     * The mouse has been pressed.
     */
    MOUSE_BUTTON_INTERACT,

    /**
     * The mouse has been scrolled.
     */
    MOUSE_SCROLLED,

    /**
     * Anything else or it's unknown.
     */
    ANY
}
