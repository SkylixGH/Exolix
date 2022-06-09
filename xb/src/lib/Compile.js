import Compiler from "./Compiler.js";
import Project from "./Project.js";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";
import generateDeclarations from "./GenerateDeclarations.js";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projects = fs.readdirSync(path.join(dirName, "../../../packages/"));

export default function compile(watch) {
    projects.forEach(project => {
        const p = new Project(project);
        const clr = new Compiler(p);

        clr.run().then();
        generateDeclarations(p, watch);
    });
}
