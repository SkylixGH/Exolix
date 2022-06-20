import fs from "fs";
import { EventEmitter } from "events";
import path from "path";
import { fileURLToPath } from "url";
import chokidar from "chokidar";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);

export default class Project extends EventEmitter {
    #name;
    #package = {};

    constructor(name) {
        super();

        this.#name = name;

        this.#package = JSON.parse(fs.readFileSync(path.join(dirName, "../../../packages/", name, "package.json"), "utf8"));
    }

    autoEmitChanges() {
        chokidar.watch(this.path, {
            ignored: [
                path.join(this.path, "node_modules"),
                path.join(this.path, "build"),
            ],
            ignoreInitial: true
        }).on("all", (event, path) => {
            this.emit("change", event, path);
        });
    }

    get name() {
        return this.#name;
    }

    get pkg() {
        return this.#package;
    }

    set pkg(pkg) {
        this.#package = pkg;
        fs.writeFileSync(path.join(dirName, "../../../packages/", this.#name, "package.json"), JSON.stringify(this.#package, null, 4) + "\n");
    }

    get path() {
        return path.join(dirName, "../../../packages/", this.#name);
    }

    get elixorRoot() {
        return path.join(dirName, "../../../");
    }

    get tsconfig() {
        return JSON.parse(fs.readFileSync(path.join(this.path, "tsconfig.json"), "utf8"));
    }

    get tsconfigPath() {
        return path.join(this.path, "tsconfig.json");
    }
}
