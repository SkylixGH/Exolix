import System from "./system/System";
import { createRequire } from "module";

const require = createRequire(import.meta.url);
const modulee = require("../Release/native.node");

modulee.hello();

export { System };
