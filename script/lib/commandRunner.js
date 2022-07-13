import { exec } from 'child_process';
import * as path from "path";
import {projectRoot} from "./meta.js";

/**
 * All running process IDs
 * @type {number[]}
 */
const runningProcesses = [];

/**
 * Run a command relative from the source root.
 *
 * @param location The location CWD relative to the source root.
 * @param command The command to run.
 * @param name The command process name, this isn't the executable, just an identifier for when logging.
 * @param onDone Callback when the command is done.
 * @return The process ID.
 */
export function commandRunner(location, command, name, onDone = () => {}) {
    console.log(`[ Info ] Executing '${command}' for ${name}`);
    console.log(`[ Info ] CWD: ${path.join(projectRoot, location)}`);

    const processSpawn = exec(command ?? "echo err", {
        cwd: path.join(projectRoot, location)
    });

    processSpawn.stdout.on("data", data => {
        data.split("\n").forEach(line => {
            console.log(`[ ${name} ] OUT: ${line}`);
        });
    });

    processSpawn.stderr.on("data", data => {
        data.split("\n").forEach(line => {
            console.error(`[ ${name} ] ERR: ${line}`);
        });
    });

    processSpawn.on("close", code => {
        console.log(`[ ${name} ] Exit code: ${code}`);
        onDone?.(code);
    });

    runningProcesses.push(processSpawn.pid);
    return processSpawn.pid;
}

export async function commandRunnerAsync(location, command, name) {
    return new Promise((resolve, reject) => {
        commandRunner(location, command, name, resolve);
    });
}

/**
 * Kill a process by its PID.
 * @param pid The process ID to kill.
 */
export function commandKiller(pid) {
    console.log(`[ Info ] Killing process ${pid}`);

    try {
        process.kill(pid);
    } catch (error) {
        console.log(`[ Error ] Failed to kill process ${pid}`);
    }
}
