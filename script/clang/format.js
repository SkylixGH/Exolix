import {commandRunner} from "../lib/commandRunner.js";
import * as path from "path";
import * as fs from "fs";

function formatDir(loc) {
    commandRunner(loc, `clang-format -i -style=llvm *.cxx *.hxx *.h *.c`, "Clang Format");
}

function recursiveFormat(loc) {
    const files = fs.readdirSync(loc);

    files.forEach(file => {
        const filePath = path.join(loc, file);
        const stats = fs.statSync(filePath);

        if (stats.isDirectory()) {
            formatDir(loc);
            recursiveFormat(filePath);
        }
    });
}

recursiveFormat(".");
console.log("[ Info ] Formatting complete successfully, if you see any 'No such file or directory' errors, that's completely normal.");
