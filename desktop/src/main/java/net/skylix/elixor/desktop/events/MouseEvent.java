package net.skylix.elixor.desktop.events;

/**
 * A mouse event.
 *
 * @param x The x position of the mouse.
 * @param y The y position of the mouse.
 * @param button The button that was pressed.
 * @param pressed The pressed state of the button.
 * @param type The mouse event trigger.
 */
public record MouseEvent(int x, int y, int button, boolean pressed, MouseEventType type) {}
