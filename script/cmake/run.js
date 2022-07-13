import {compile} from "../lib/compile.js";
import {spawn} from "child_process";
import * as path from "path";
import * as fs from "fs";
import {projectRoot} from "../lib/meta.js";

async function runScratchProject(id) {
    const file = (id == 0 ? "main" : `Scratch${id}`) + (process.platform === "win32" ? ".exe" : "");
    const subDirectory = process.platform === "win32" ? (
        fs.existsSync(path.join(projectRoot, "build", "Release")) ? "Release" : "Debug"
    ) : "./";
    await compile();

    const buildPath = path.join(projectRoot, "build", subDirectory, file);
    spawn(buildPath, [], {
        stdio: "inherit",
    });
}

await runScratchProject(0);
