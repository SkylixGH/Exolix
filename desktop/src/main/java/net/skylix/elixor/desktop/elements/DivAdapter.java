package net.skylix.elixor.desktop.elements;

import java.util.ArrayList;

/**
 * An event listening adapter.
 */
public abstract class DivAdapter {
    /**
     * Listen for mouse events.
     * @param event The div mouse event.
     */
    public abstract void onMouseEvent(DivMouseEvent event);
}
