#!/usr/bin/env node

const { spawn } = require("child_process");

spawn("../node_modules/.bin/ts-node", [
    "--transpile-only",
    "../src/index.ts"
], {
    cwd: __dirname,
    stdio: "inherit",
});
