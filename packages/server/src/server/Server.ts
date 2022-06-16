import http, { IncomingMessage, Server as HServer, ServerOptions, ServerResponse } from "http";
import https, { ServerOptions as HttpsOptions } from "https";
import SystemPort from "../sys/SystemPort";
import ServerSsl from "./ServerSsl";

/**
 * A class used for creating API servers.
 */
export default class Server {
    /**
     * The underlying core HTTP/s server.
     */
    #server: HServer | undefined;

    /**
     * If the server is running.
     */
    #running = false;

    /**
     * The SSL certificate information for the server.
     */
    #ssl: ServerSsl = false;

    /**
     * The port that the server is allowed to listen on.
     */
    #port: SystemPort | false = false;

    /**
     * The hostname for the server.
     */
    #hostname = "localhost";

    /**
     * The backlog for the server, which is the number of connections that can be queued.
     */
    #backlog = 100;

    /**
     * Create a new instance of the API server.
     */
    public constructor() {
        this.#initHttpServer();
    }

    /**
     * Run the API gateway server.
     */
    public async run() {
        if (this.#running) return;
        this.#running = true;

        const createServer = () => new Promise<void>((resolve, reject) => {
            try {
                this.#server?.listen(this.#port === false ? undefined : this.#port, this.#hostname, this.#backlog, () => {
                    resolve();
                });
            } catch (e) {
                reject(e);
            }
        });

        await createServer();
    }

    /**
     * Reinitialize the http(s) server class with the current settings.
     */
    #initHttpServer() {
        const httpSettings: ServerOptions & HttpsOptions = {};

        if (this.#ssl) {
            httpSettings.key = this.#ssl.key;
            httpSettings.cert = this.#ssl.cert;
        }

        const REX: any = {};
        const OPIX: any = {};

        this.#server?.close();
        this.#server = (this.#ssl !== false ? https : http).createServer(httpSettings, this.#requestCallback);
    }

    /**
     * The HTTP(s) request callback.
     * 
     * @param req The request.
     * @param res The response.
     */
    #requestCallback(req: IncomingMessage, res: ServerResponse) {
        res.writeHead(200, { "Content-Type": "text/plain" });
        res.end("Hello World\n");

        console.log(`${req.method} ${req.url}`);
        res.end();
    }

    /**
     * Handle a server setting change queue, that requires a restart.
     */
    #handleSettingChanging() {
        if (this.#running) {
            // TODO: Throw error
        }
    }

    /**
     * Set the server SSL certificate.
     */
    public set ssl(ssl: ServerSsl) {
        this.#handleSettingChanging();
        this.#ssl = ssl;

        this.#initHttpServer();
    }

    /**
     * Get the server SSL certificate.
     */
    public get ssl(): ServerSsl {
        return this.#ssl;
    }

    /**
     * Set the server port.
     */
    public set port(port: SystemPort | false) {
        this.#handleSettingChanging();
        this.#port = port;

        this.#initHttpServer();
    }

    /**
     * Get the server port.
     */
    public get port(): SystemPort | false {
        return this.#port;
    }

    /**
     * Set the server hostname.
     */
    public set hostname(hostname: string) {
        this.#handleSettingChanging();
        this.#hostname = hostname;

        this.#initHttpServer();
    }

    /**
     * Get the server hostname.
     */
    public get hostname(): string {
        return this.#hostname;
    }

    /**
     * Set the server backlog.
     */
    public set backlog(backlog: number) {
        this.#handleSettingChanging();
        this.#backlog = backlog;

        this.#initHttpServer();
    }

    /**
     * Get the server backlog.
     */
    public get backlog(): number {
        return this.#backlog;
    }
}
