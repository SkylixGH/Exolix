import EventMap from "./EventMap";

/**
 * An event emitter for a class.
 */
export default class Emitter<EventTable extends EventMap> {
    /**
     * All of the event listeners.
     */
    #listeners: CallableFunction[] = [];

    /**
     * Emit an event.
     * 
     * @param name The name of the event.
     * @param args All the args of the event output.
     */
    public emit(name: keyof EventTable, args: Parameters<EventTable[keyof EventTable]>) {
        if (this.#listeners.length > 0)
            this.#listeners.forEach((l) => l());
    }

    /**
     * Add an event listener to a specific event.
     *
     * @param name The name of the event.
     * @param listener The listener for the event.
     */

}
