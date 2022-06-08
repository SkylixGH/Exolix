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
}
