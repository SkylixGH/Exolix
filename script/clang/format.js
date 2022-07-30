import * as fs from "fs";
import * as path from "path";

import {commandRunner} from "../lib/commandRunner.js";
import {projectRoot} from "../lib/meta.js";

const ext = [ "*.cxx", "*.hxx", "*.h", "*.c", "*.js", "*.json" ]

    function formatDir(loc) {
        commandRunner(loc, `clang-format -i -style=file ${ext.join(" ")}`,
                      "Clang Format");
    }

const ignoredFromRoot = [
    "build", ".git", "resource", "script/node_modules", "cmake", ".clang-format"
];

function recursiveFormat(loc) {
    const files = fs.readdirSync(path.join(projectRoot, loc));

    files.forEach(file => {
        const filePath = path.join(loc, file);
        const stats = fs.statSync(path.join(projectRoot, filePath));

        let containsBadDir = false;

        ignoredFromRoot.forEach(ignored => {
            if (filePath.startsWith(path.join(ignored))) {
                containsBadDir = true;
            }
        });

        if (stats.isDirectory() && !containsBadDir) {
            console.log(`[ Try ] Recursing into ${filePath}`);

            formatDir(filePath);
            console.log("");

            recursiveFormat(filePath);
        }
    });
}

recursiveFormat("");
console.log(
    "[ Info ] Formatting complete successfully, if you see any 'No such file or directory' errors, that's completely normal.");
