import { Lexer, TokenStructure } from "@skylixgh/elixor-lexer";

interface Tokens extends TokenStructure {
    newLine: RegExp;
    lParen: RegExp;
    rParen: RegExp;
    text: RegExp;
    space: RegExp;
    quote: RegExp;
    semiColon: RegExp;
}

class LanguageCompiler {
    readonly lexer: Lexer<Tokens>;

    public constructor(source: string) {
        const tokens: Tokens = {
            newLine: /^\n/,
            lParen: /^\(/,
            rParen: /^\)/,
            space: /^\s+/,
            quote: /^"/,
            semiColon: /^;/,
            text: /^[^\(\)\n]+/,
        };

        this.lexer = new Lexer(tokens, source);
        this.#parse();
    }

    #parse() {
        const tokens = this.lexer.tokens;
        console.log(tokens);
    }
}

const clr = new LanguageCompiler(`print("Hello World");\n`);
