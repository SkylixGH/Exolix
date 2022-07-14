import * as fs from "fs";
import * as path from "path";

import {commandRunner, commandRunnerAsync} from "./commandRunner.js";
import {projectRoot} from "./meta.js";

export async function cmakeInit() {
    const buildExists =
        fs.existsSync(path.join(projectRoot, "build")) &&
        fs.statSync(path.join(projectRoot, "build")).isDirectory() &&
        fs.readdirSync(path.join(projectRoot, "build")).length > 0;

    if (!buildExists) {
        fs.mkdirSync(path.join(projectRoot, "build"));
        const returnCode =
            await commandRunnerAsync("build", "cmake ..", "CMake Init");

        if (returnCode !== 0) {
            throw new Error("CMake Init failed");
        }
    }
}

export async function cmakeBuild(release) {
    const buildExists =
        fs.existsSync(path.join(projectRoot, "build")) &&
        fs.statSync(path.join(projectRoot, "build")).isDirectory() &&
        fs.readdirSync(path.join(projectRoot, "build")).length > 0;

    if (!buildExists) {
        throw new Error("CMake Build failed because it was not initialized");
    }

    const returnCode = await commandRunnerAsync(
        "build", "cmake --build . --config " + (release ? "Release" : "Debug"),
        "CMake Build");

    if (returnCode !== 0) {
        throw new Error("CMake Build failed");
    }
}

export async function compile() {
    await cmakeInit();
    await cmakeBuild();
}
