import { Server as HServer } from "http";

/**
 * A class used for creating API servers.
 */
export default class Server {
    /**
     * The underlying core HTTP/s server.
     */
    readonly #server: HServer;

    /**
     * Create a new instance of the API server.
     */
    public constructor() {

    }
}
