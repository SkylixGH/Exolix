import * as path from "path";
import {fileURLToPath} from "url";

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projectRoot = path.join(dirName, "../../");

export {projectRoot}
