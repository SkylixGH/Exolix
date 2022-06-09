import Compiler from "./Compiler.js";
import Project from "./Project.js";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projects = fs.readdirSync(path.join(dirName, "../../../packages/"));

export default function compile() {
    projects.forEach(project => {
        const clr = new Compiler(new Project(project));
        clr.run().then();
    });
}
