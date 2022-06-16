import EventMap from "./EventMap";

/**
 * An event emitter for a class.
 */
export default class Emitter<EventTable extends EventMap> {
    /**
     * All of the event listeners.
     */
    #listeners: EventTable[] = [];

    /**
     * Emit an event.
     * 
     * @param name The name of the event.
     * @param args All the args of the event output.
     */
    public emit(name: keyof EventTable) {
    }
}
