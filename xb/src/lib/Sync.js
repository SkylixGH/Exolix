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

        const npmIgnore = "node_modules/" +
            "\nsrc/\n";

        fs.writeFileSync(
            path.join(data.path, ".npmignore"),
            npmIgnore
        );

        const packageData = {
            name: opkg.name,
            type: "module",
            version: opkg.version,
            description: opkg.description,
            homepage: opkg.homepage,
            main: "./build/cjs/build.cjs",
            types: "./build/types/Main.d.ts",
            cxx: opkg.cxx,
            exports: {
                import: "./build/esm/build.js",
                require: "./build/cjs/build.cjs"
            },
            author: {
                name: opkg.author.name,
                email: opkg.author.email,
                url: opkg.author.url
            },
            dependencies: {
                ...opkg.dependencies,
                "source-map-support": "^0.5.21",
                "@types/node": "^17.0.40",
                ...(opkg.cxx ? { 
                    "nan": "^2.16.0",
                    "cmake-js": "^6.3.2" 
                } : {}),
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
        "devDependencies": {
            "esbuild": "^0.14.43"
        },
        "scripts": {
            "test": "esbuild Scratch.ts --outfile=build/Scratch.mjs && node build/Scratch.mjs",
        }
    };

    fs.writeFileSync(path.join(dirName, "../../../xs/package.json"), JSON.stringify(scratchJSON, null, 4) + "\n");
}
