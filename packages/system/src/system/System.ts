/**
 * A class for accessing system utils.
 */
export default class System {
    /**
     * If the process is running with elevated privileges.
     */
    public static get isElevated() {
        const os = this.osType;

        if (os === "Unix")
            if (typeof process.getuid === "function")
                return process.getuid() !== 0;
            else
                return false;
        else if (os === "Windows")
            console.log("ERR");
    }

    /**
     * The operating system type.
     */
    public static get osType() {
        let type: "Unix" | "Windows" | "Other" = "Other";

        if (process.platform === "win32") {
            type = "Windows";
        } else if (process.platform === "linux" || process.platform === "freebsd" || process.platform === "openbsd" || process.platform === "sunos") {
            type = "Unix";
        } else if (process.platform === "darwin") {
            type = "Unix";
        }

        return type;
    }
}
