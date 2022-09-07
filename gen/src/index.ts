import { Command } from "commander";
import {initGenerateIncludeCommand} from "./commands/generateIncludeFIle";

const program = new Command();

program
    .version("Unknown")
    .description("Utility C++ library helper")
    .name("cmake-assist-generator");

initGenerateIncludeCommand(program);

program.parse(process.argv);
