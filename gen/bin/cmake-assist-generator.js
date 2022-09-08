#!/usr/bin/env node

const {spawn} = require("child_process");
const path = require("path");

spawn(path.join(__dirname, "../node_modules/.bin/ts-node"), [
    "--transpile-only",
    path.join(__dirname, "../src/index.ts"),
    ...process.argv.slice(2)
], {
    cwd: process.cwd(),
    stdio: "inherit",
});
