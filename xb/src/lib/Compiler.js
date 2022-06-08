import esbuild from "esbuild";
import path from "path";
import { fileURLToPath } from "url";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);

export default class Compiler {
    #cjs;
    #esm;

    #project;

    constructor(project) {
        this.#project = project;
    }

    async run() {
        const def = {
            entryPoints: [ path.join(dirName, "../../../packages/", this.#project.name, "src/Main.ts")],
            bundle: true,
            external: Object.keys(this.#project.pkg.dependencies ?? {}),
        };

        this.#esm = await esbuild.build({
            ...def,
            outfile: path.join(dirName, "../../../packages/", this.#project.name, "build/esm/build.js"),
            format: "esm",
        });

        this.#cjs = await esbuild.build({
            ...def,
            outfile: path.join(dirName, "../../../packages/", this.#project.name, "build/cjs/build.js"),
            format: "cjs",
        });
    }
}