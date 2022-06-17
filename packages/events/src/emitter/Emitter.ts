import EventMap from "./EventMap";

/**
 * An event emitter for a class.
 */
export default class Emitter<EventTable extends EventMap> {
    /**
     * All of the event listeners.
     */
    #listeners: { [ key: string ]: {
        /**
         * The event method.
         */
        method: CallableFunction,

        /**
         * The event name.
         */
        name: string
    } } = {};

    /**
     * Emit an event.
     * 
     * @param name The name of the event.
     * @param args All the args of the event output.
     */
    public emit(name: keyof EventTable, ...args: Parameters<EventTable[keyof EventTable]>) {
        if (Object.keys(this.#listeners).length > 0)
            Object.keys(this.#listeners)
                .filter((l, i) => this.#listeners[l].name === name)
                .forEach((l, i) => this.#listeners[l].method(...args ?? []));
    }

    /**
     * Add an event listener to a specific event.
     *
     * @param name The name of the event.
     * @param listener The listener for the event.
     * @return The pointer to the event listener.
     */
    public on(name: keyof EventTable, listener: (...args: Parameters<EventTable[keyof EventTable]>) => void) {
        const id = this.#genPointer();

        this.#listeners[id.str] = {
            method: listener,
            name: name as string
        };

        return id.str;
    }

    /**
     * Remove an event listener from a specific event.
     * 
     * @param pointer The pointer to the event listener.
     */
    public off(pointer: string) {
        delete this.#listeners[pointer];
    }

    /**
     * Generate a random string.
     * 
     * @param len The length of the string.
     * @returns The result object with the max possible combinations and the random string.
     */
    #randKey(len: number) {
        const charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        const possibleCombinations = Math.pow(charSet.length, len);

        let randKey = "";
        for (let i = 0; i < len; i++)
            randKey += charSet.charAt(Math.floor(Math.random() * charSet.length));

        return {
            possibleCombinations,
            str: randKey
        };
    }

    /**
     * Generate a unique ID for a listener.
     * 
     * @returns The pointer unique ID for the listener.
     */
    #genPointer() {
        let len = 1;
        let result = this.#randKey(len);

        const revalidate = () => {
            if (this.#listeners.hasOwnProperty(result.str)) {
                if (Object.keys(this.#listeners).length >= result.possibleCombinations)
                    len++;

                result = this.#randKey(len);
                revalidate();
            }
        }

        if (this.#listeners.hasOwnProperty(result.str))
            revalidate();

        return result;
    }
}
