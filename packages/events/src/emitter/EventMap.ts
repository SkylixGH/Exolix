/**
 * An event structure for the event emitter.
 */
export default interface EventMap {
    [ key: string ]: ( ...args: any[] ) => void;
}
