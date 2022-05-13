package net.skylix.elixor.desktop.elements;

import java.util.ArrayList;

/**
 * An event listening adapter.
 */
public abstract class DivAdapter {
    /**
     * All listeners.
     */
    protected ArrayList<DivAdapter> listeners = new ArrayList<>();

    /**
     * Listen for mouse events.
     * @param event The div mouse event.
     */
    public abstract void onMouseEvent(DivMouseEvent event);

    /**
     * Add an event listener.
     * @param listener The listener to add.
     */
    public void addListener(DivAdapter listener) {
        listeners.add(listener);
    }

    /**
     * Remove an event listener.
     * @param listener The listener to remove.
     */
    public void removeListener(DivAdapter listener) {
        listeners.remove(listener);
    }

    /**
     * Execute the onMouseEvent method on all listeners.
     * @param event The div mouse event.
     */
    protected void executeOnMouseEvent(DivMouseEvent event) {
        for (DivAdapter listener : listeners) {
            listener.onMouseEvent(event);
        }
    }
}
