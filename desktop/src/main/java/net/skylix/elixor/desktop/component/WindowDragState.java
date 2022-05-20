package net.skylix.elixor.desktop.component;

/**
 * The window drag state.
 */
public enum WindowDragState {
    /**
     * Don't do anything.
     */
    NONE,

    /**
     * Allow the window to move when this element is dragged.
     */
    ALLOW,

    /**
     * If a parent element has drag, this one will deny the window to move.
     */
    DENY
}
