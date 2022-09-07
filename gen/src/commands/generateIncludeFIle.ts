import * as fs from "fs";
import * as path from "path";
import {Command} from "commander";

interface DataParsedCap {
    type: "start" | "end";
    name: string;
}

interface DataParsedContent {
    type: "value";
    value: string;
}

type DataParsed = DataParsedCap | DataParsedContent;

interface Card {
    name: string;
    content: string;
}

class CxxIncludeFile {
    public exactPath: string;
    public libPath: string;

    private parsedTrue: DataParsed[] = [];
    private cardsTrue: Card[] = [];

    constructor(exactPath: string, libPath: string) {
        this.exactPath = exactPath;
        this.libPath = libPath;

        if (!fs.existsSync(exactPath)) {
            throw new Error("File does not exist: " + exactPath);
        }

        if (!fs.lstatSync(exactPath).isFile()) {
            throw new Error("Not a file: " + exactPath);
        }

        if (!fs.existsSync(libPath)) {
            throw new Error("File does not exist: " + libPath);
        }

        if (!fs.lstatSync(libPath).isDirectory()) {
            throw new Error("Not a directory: " + libPath);
        }

        this.parse();
    }

    private parse() {
        const lines = fs.readFileSync(this.exactPath, "utf8")
            .replace(/\r\n/g, "\n")
            .split("\n");

        let opened = false;
        let capture = "";

        lines.forEach((line) => {
            console.log(line);

            if (line.startsWith("// #Start")) {
                if (!opened) {
                    opened = true;

                    const name = line.substring(9).trim();
                    const data: DataParsed = {
                        type: "start",
                        name
                    };

                    this.parsedTrue.push(data);
                } else capture += line + "\n";
            } else if (line.startsWith("// #End")) {
                if (opened) {
                    opened = false;

                    const data: DataParsed = {
                        type: "end",
                        name: line.substring(7).trim()
                    };

                    this.parsedTrue.push(data);
                } else capture += line + "\n";
            } else {
                if (opened) {
                    const data: DataParsed = {
                        type: "value",
                        value: line
                    };

                    this.parsedTrue.push(data);
                }
            }
        });

        // now we need to simplify the data so that we can easily read it.
        let currentCard: Card | null = null;

        this.parsedTrue.forEach((data) => {
            if (data.type === "start") {
                if (currentCard) {
                    throw new Error("Unexpected start");
                }

                currentCard = {
                    name: data.name,
                    content: ""
                };
            } else if (data.type === "end") {
                if (!currentCard) {
                    throw new Error("Unexpected end");
                }

                if (data.name !== currentCard.name) {
                    throw new Error("Unexpected end");
                }

                this.cardsTrue.push(currentCard);
                currentCard = null;
            } else if (data.type === "value") {
                if (!currentCard) {
                    throw new Error("Unexpected value");
                }

                currentCard.content += data.value + "\n";
            }
        });
    }

    public get parsed() {
        return [ ...this.parsedTrue ];
    }

    public get cards() {
        return [ ...this.cardsTrue ];
    }
}

export function initGenerateIncludeCommand(program: Command) {
    program
        .command("generate-include")
        .description("Generate include file")
        .argument("<relative-path-to-include-file>", "Relative path to include file")
        .argument("<relative-path-to-cxx-lib-dir>", "Relative path to C++ library directory")
        .action((
            relativePathToIncludeFile: string,
            relativePathToCxxLibDir: string
        ) => {
            const pathTo = path.join(process.cwd(), relativePathToIncludeFile);
            const libTo = path.join(process.cwd(), relativePathToCxxLibDir);

            console.log("Generating include file: " + pathTo + "...");

            const includeFile = new CxxIncludeFile(pathTo, libTo);

            console.log(includeFile.cards);
        });
}
