// Simple markdown parser

import { Lexer, TokenUtil } from "@skylixgh/elixor-lexer";

interface Tokens {
    newLine: RegExp;
    comment: RegExp;
    header: RegExp;
    codeFence: RegExp;
    space: RegExp;
    text: RegExp;
}

const tree: Tokens = {
    newLine: /^\n/,
    comment: /^\/\//,
    header: /^#{1,6}\s+/,
    codeFence: /^```/,
    space: /^\s+/,
    text: /^[^\n]+/
}

class MD {
    constructor(data: string) {
        const lexer = new Lexer<Tokens>(tree, data);
        const tokens = lexer.tokens;
        const util = new TokenUtil(lexer);

        const ast = [] as any[];

        let contextOf = null as null | string;
        let mesh: any;

        tokens.forEach((token, index) => {
            if (contextOf === null) {
                if (token.type === "header") {
                    mesh = {
                        type: "header",
                        level: token.value.length - 1,
                    };

                    contextOf = "header:an";
                }
            } else {
                if (contextOf === "header:an") {
                    // find everything from here to the end of the line
                    const values = util.getAllUntil({
                        indexCharStart: token.start,
                    }, (tk) => tk.type === "newLine");

                    mesh.values = values;
                    
                    ast.push(mesh);
                    contextOf = null;
                }
            }
        });

        console.log(ast[0], ast[1]);
    }
} 

// const p = new MD(`# Hello World
// ## Hello Worldd praty kewl
// ######Invalid
// \`\`\typescript
// eyy hi
// \`\`\``);


