import {commandRunner} from "../lib/commandRunner.js";
import * as path from "path";
import * as fs from "fs";
import {projectRoot} from "../lib/meta.js";

function formatDir(loc) {
    commandRunner(loc, `clang-format -i -style=InheritParentConfig *.cxx *.hxx *.h *.c`, "Clang Format");
}

function recursiveFormat(loc) {
    const files = fs.readdirSync(path.join(projectRoot, loc));

    files.forEach(file => {
        const filePath = path.join(loc, file);
        const stats = fs.statSync(path.join(projectRoot, filePath));

        if (stats.isDirectory()) {
            console.log(`[ Try ] Recursing into ${filePath}`);

            formatDir(filePath);
            console.log("");

            recursiveFormat(filePath);
        }
    });
}

recursiveFormat("elixor");
console.log("[ Info ] Formatting complete successfully, if you see any 'No such file or directory' errors, that's completely normal.");
