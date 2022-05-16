package net.skylix.elixor.desktop.window;

import java.util.ArrayList;

/**
 * A window adapter for listening to window events
 */
public abstract class WindowAdapter {
    /**
     * Listen for mouse events.
     *
     * @param event The window mouse event.
     */
    public abstract void onMouseEvent(WindowMouseEvent event);
}
