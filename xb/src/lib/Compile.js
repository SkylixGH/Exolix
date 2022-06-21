import Compiler from "./Compiler.js";
import Project from "./Project.js";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";
import generateDeclarations from "./GenerateDeclarations.js";
import compileCxx from "./CompileCxx.js";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projects = fs.readdirSync(path.join(dirName, "../../../packages/"));

export default function compile(watch) {
    projects.forEach(async (project) => {
        const p = new Project(project);
        const clr = new Compiler(p);

        await clr.run();
        await compileCxx(p, true);
        await generateDeclarations(p, watch);

        if (watch)
            p.autoEmitChanges();
        else
            process.exit(0);

        p.on("change", (event, path) => {
            clr.run().then();

            if (path.endsWith(".cxx") || path.endsWith(".hxx"))
                compileCxx(p, true);
                
            console.log("Changed:", event, path);
        });
    });
}
