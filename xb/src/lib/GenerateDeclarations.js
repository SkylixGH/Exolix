import chokidar from "chokidar";
import { spawn } from "child_process";
import path from "path";

export default function generateDeclarations(project, watch) {
    const flags = [
        "--declarationDir", path.join(project.path, "build/types"),
        "--emitDeclarationOnly",
        "--rootDir", path.join(project.path, "src"),
        "--project", project.tsconfigPath,
        ...(watch ? ["--watch"] : []),
    ];

    spawn(path.join(project.elixorRoot, "./node_modules/typescript/bin/tsc"), flags, {
        stdio: "inherit",
        cwd: path.join(project.elixorRoot)
    }).on("error", () => {
        spawn("npx" + (process.platform === "win32" ? ".cmd" : ""), [ "tsc", ...flags ], {
            stdio: "inherit",
            cwd: path.join(project.elixorRoot)
        });
    });
}