import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const dirName = path.dirname(fileURLToPath(import.meta.url));
const includeDirPathFull = path.join(dirName, "../../inc");
const pathToIncludeFile = path.join(includeDirPathFull, "exolix.hh");
const allFilePathsRelative = [];

let includesFile = "#pragma once\n\n";

function walk(dir) {
    const entries = fs.readdirSync(dir);

    for (const entry of entries) {
        const entryPath = path.join(dir, entry);
        const stat = fs.statSync(entryPath);

        if (stat.isDirectory()) {
            walk(entryPath);
        } else {
            allFilePathsRelative.push(path.relative(includeDirPathFull, entryPath));
        }
    }
}

walk(path.join(includeDirPathFull, "exolix"));

allFilePathsRelative.forEach(filePath => {
    includesFile += `#include <${filePath}>\n`;
});

fs.writeFileSync(pathToIncludeFile, includesFile);
