package net.skylix.elixor.desktop.unit;

import java.util.ArrayList;

/**
 * A basic unit.
 */
class Unit {
    /**
     * All of the listeners.
     */
    protected ArrayList<UnitAdapter> listeners = new ArrayList<>();

    /**
     * Add a listener to the unit.
     * @param listener The listener to add.
     */
    public void addListener(UnitAdapter listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener from the unit.
     * @param listener The listener to remove.
     */
    public void removeListener(UnitAdapter listener) {
        listeners.remove(listener);
    }

    /**
     * Execute all of the change listeners.
     */
    protected void executeOnChange() {
        for (UnitAdapter listener : listeners) {
            listener.onChange();
        }
    }
}
