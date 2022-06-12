import { Lexer, TokenUtil } from "@skylixgh/elixor-lexer";

function se(msg: string, codep: string, e: string, codea: string) {
    console.error("Syntax Error : Unexpected Token : " + msg + "\n");
    console.log(codep + e + codea);
    console.log(" ".repeat(codep.length) + "^".repeat(e.length));

    process.exit(1);
}

interface Tokens {
    newLine: RegExp;
    comment: RegExp;
    if: RegExp;
    true: RegExp;
    false: RegExp;
    lParen: RegExp;
    rParen: RegExp;
    lBrace: RegExp;
    rBrace: RegExp;
    text: RegExp;
    space: RegExp;
    quote: RegExp;
    semiColon: RegExp;
}

interface Node {
    nodes: Node[];
    type: keyof Tokens;
    value?: string;
}

class LanguageCompiler {
    readonly lexer: Lexer<Tokens>;

    public constructor(source: string) {
        const tokens: Tokens = {
            newLine: /^\n/,
            comment: /^\^-\^/,
            if: /^if\b/,
            true: /^true\b/,
            false: /^false\b/,
            lParen: /^\(/,
            rParen: /^\)/,
            lBrace: /^\{/,
            rBrace: /^\}/,
            space: /^\s/,
            quote: /^"/,
            semiColon: /^;/,
            text: /^[^\(\)\n]+/,
        };

        this.lexer = new Lexer(tokens, source);
        this.#parse();
    }

    #parse() {
        const tokens = this.lexer.tokens;
        const util = new TokenUtil<Tokens>(this.lexer);
        const ast: Node[] = [];

        tokens.forEach((token, index) => {
            if (token.type === "if") {
                const hlp = util.getAfter({
                    indexStart: token.start,
                }, "lParen")!;

                if (!hlp) {
                    se("Error missing LParen", "if (", "???", ") {");
                }

                const hcond = util.findUntil({
                    indexStart: hlp.end
                }, (t) => {
                    return t.type === "true" || t.type === "false";
                })!;

                if (!hcond) {
                    se("Error missing condition", "if (", "???", ") {");
                }

                const hlp2 = util.findUntil({
                    indexStart: hcond.end
                }, (t) => {
                    return t.type === "rParen";
                });

                if (!hlp2) {
                    se("Error missing RParen", "if (", "???", ") {");
                }

                ast.push({
                    nodes: [
                        {
                            type: hcond?.value === "true" ? "true" : "false",
                            nodes: []
                        }
                    ],
                    type: "if",
                })
            }
        });

        console.log(ast[0]);
    }
}

const clr = new LanguageCompiler(`print("Hello World");
if                                        
              (                    false) {
    print("Hello World True");
}`);
