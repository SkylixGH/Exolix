package net.skylix.elixor.desktop.window;

import net.skylix.elixor.desktop.events.MouseEvent;

import java.util.ArrayList;

/**
 * A window adapter for listening to window events
 */
public class WindowAdapter {
    /**
     * Listen for when the mouse is moved.
     *
     * @param event The mouse event.
     */
    public void onMouseInteract(MouseEvent event) {}

    /**
     * Listen for when the window is resized.
     */
    public void onResize() {}
}
