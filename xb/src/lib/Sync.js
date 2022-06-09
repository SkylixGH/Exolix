import Project from "./Project.js";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projects = fs.readdirSync(path.join(dirName, "../../../packages/"));

export default function sync() {
    const elixorDependencies = {};

    projects.forEach(project => {
        const data = new Project(project);
        const opkg = data.pkg;

        elixorDependencies[project] = "file:../packages/" + project;

        const packageData = {
            name: opkg.name,
            type: "module",
            version: opkg.version,
            description: opkg.description,
            homepage: opkg.homepage,
            main: "./build/cjs/build.js",
            types: "./build/types/Main.d.ts",
            exports: {
                import: "./build/esm/build.js",
                require: "./build/cjs/build.js"
            },
            author: {
                name: opkg.author.name,
                email: opkg.author.email,
                url: opkg.author.url
            },
            dependencies: {
                ...opkg.dependencies,
                "source-map-support": "^0.5.21",
            },
            devDependencies: {
                ...opkg.devDependencies,
            },
        }

        Object.keys(opkg).forEach(key => {
            if (!packageData.hasOwnProperty(key)) {
                packageData[key] = opkg[key];
            }
        });

        data.pkg = packageData;
    });

    const scratchJSON = {
        "name": "xs",
        "type": "module",
        "dependencies": elixorDependencies,
    };

    fs.writeFileSync(path.join(dirName, "../../../xs/package.json"), JSON.stringify(scratchJSON, null, 4) + "\n");
}
