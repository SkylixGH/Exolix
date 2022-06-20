import System from "./system/System";
import { createRequire } from "module";

const require = createRequire(import.meta.url);
const module = require("../Release/native.node");

console.log(module);

export { System };
