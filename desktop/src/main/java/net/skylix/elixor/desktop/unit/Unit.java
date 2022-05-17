package net.skylix.elixor.desktop.unit;

import java.util.ArrayList;

/**
 * A basic unit.
 */
class Unit {
    /**
     * Whether the event firings are paused.
     */
    private boolean pauseOnChange = false;

    /**
     * All the listeners.
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
     * Execute all the change listeners.
     */
    protected void executeOnChange() {
        if (pauseOnChange) return;

        for (UnitAdapter listener : listeners) {
            listener.onChange();
        }
    }

    /**
     * Pause the on change event firing.
     */
    public void pauseOnChange() {
        pauseOnChange = true;
    }

    /**
     * Get whether the on change event firing is paused.
     *
     * @return Whether the on change event firing is paused.
     */
    public boolean isPausedOnChange() {
        return pauseOnChange;
    }

    /**
     * Resume the on change event firing.
     */
    public void resumeOnChange() {
        pauseOnChange = false;
    }
}
