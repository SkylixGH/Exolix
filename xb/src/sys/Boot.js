import compile from "../lib/Compile.js";
import sync from "../lib/Sync.js";

console.log("   ---   Welcome to Elixor Build System!   ---   ");
console.log("This tool is very important as its necessary to build Elixor's modules with the monorepo system and native modules.");
console.log("Depending on your processor, this shouldn't take long time to finish. \n");

console.log("   ---               Syncing               ---   ");
console.log("This will sync and clean the package, npm, and other dependency files for all modules. \n");

sync();

console.log("   ---              Compiling               ---   ");
console.log("All of the modules will now be compiled, this includes all native addons, TypeScript/JavaScript source files, and TypeScript declaration '*.d.ts' files. \n");

compile(false);

console.log("   ---   Finished building Elixor's modules   ---   ");
