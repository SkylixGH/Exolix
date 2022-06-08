import Compiler from "./lib/Compiler.js";
import Project from "./lib/Project.js";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projects = fs.readdirSync(path.join(dirName, "../../packages/"));

projects.forEach(project => {
    const clr = new Compiler(new Project(project));
    clr.run().then();
});
