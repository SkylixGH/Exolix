import { spawn } from "child_process";

let procCurr;

export default function compileRust(project, init) {
    return new Promise((resolve, reject) => {
        if (!project.pkg.rust) return;

        if (procCurr) {
            procCurr.kill();
            procCurr = null;
        }

        if (init) {
            procCurr = spawn("npm" + (process.platform === "win32" ? ".cmd" : ""), ["run", "build:rust"], {
                cwd: project.path,
                stdio: "inherit"
            }).on("exit", (code) => {
                if (code == 0) resolve();
                reject(code);
            });
        } else {
            procCurr = spawn("npm" + (process.platform === "win32" ? ".cmd" : ""), ["run", "build:rust"], {
                cwd: project.path,
                stdio: "inherit"
            }).on("exit", (code) => {
                if (code == 0) resolve();
                reject(code);
            });
        }
    });
}
