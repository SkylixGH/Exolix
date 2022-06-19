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
            sourcemap: "inline",
            platform: "node",
        };

        try {
            this.#esm = await esbuild.build({
                ...def,
                outfile: path.join(dirName, "../../../packages/", this.#project.name, "build/esm/build.js"),
                format: "esm",
                banner: { js: "/* eslint-disable */\n" +
                    "import 'source-map-support/register.js';\n" },
            });
        } catch (e) {
            console.log(e);
        }

        try {
            this.#cjs = await esbuild.build({
                ...def,
                outfile: path.join(dirName, "../../../packages/", this.#project.name, "build/cjs/build.cjs"),
                format: "cjs",
                banner: { js: "/* eslint-disable */\n" +
                    "require('source-map-support/register');\n" },
            });
        } catch (e) {
            console.log(e);
        }
    }
}