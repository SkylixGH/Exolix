import { spawn } from "child_process";
import path from "path";

export default function compileCxx(project, init) {
    if (!project.pkg.cxx) return;

    if (init) {
        const proc = spawn("npx" + (process.platform === "win32" ? ".cmd" : ""), [ "compile" ], {
            cwd: path.join(project.path, "native"),
            stdio: "inherit"
        });
    } else {
        const proc = spawn("npx" + (process.platform === "win32" ? ".cmd" : ""), [ "rebuild" ], {
            cwd: path.join(project.path, "native"),
            stdio: "inherit"
        });
    }
}
