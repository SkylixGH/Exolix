import { spawn } from "child_process";
import path from "path";

let procCurr;

export default function compileCxx(project, init) {
    if (!project.pkg.cxx) return;

    if (procCurr) {
        procCurr.kill();
        procCurr = null;
    }

    if (init) {
        procCurr = spawn("npx" + (process.platform === "win32" ? ".cmd" : ""), [ "cmake-js", "compile" ], {
            cwd: path.join(project.path, "native"),
            stdio: "inherit"
        });
    } else {
        procCurr = spawn("npx" + (process.platform === "win32" ? ".cmd" : ""), [ "cmake-js", "rebuild" ], {
            cwd: path.join(project.path, "native"),
            stdio: "inherit"
        });
    }
}
