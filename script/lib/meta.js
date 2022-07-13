import { fileURLToPath } from 'url';
import * as path from 'path';

const fileName = fileURLToPath(import.meta.url);
const dirName = path.dirname(fileName);
const projectRoot = path.join(dirName, "../../");

export {
    projectRoot
}
