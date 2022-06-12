/**
 * A thread, this thread isn't fully controlled, its powered by async/await.
 */
export default class Thread {
    /**
     * The thread body method.
     */
    readonly executor: () => void;

    /**
     * If the thread is running.
     */
    #running = false;

    /**
     * Create a new thread instance, waiting to be executed.
     * 
     * @param executor The thread body to execute.
     */
    public constructor(executor: () => void) {
        this.executor = executor;
    }

    /**
     * Execute the thread.
     */
    public run() {
        if (this.#running)
            return;

        (async () => {
            this.#running = true;
            await this.executor();
            this.#running = false;
        })();
    }

    /**
     * Check to see if the thread is running.
     */
    public get isRunning() {
        return this.#running;
    }
}
