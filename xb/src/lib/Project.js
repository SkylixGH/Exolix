import fs from "fs";
import { EventEmitter } from "events";
import path from "path";
import { fileURLToPath } from "url";

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
