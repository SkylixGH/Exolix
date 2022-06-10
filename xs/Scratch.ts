import {Lexer, TokenStructure} from "@skylixgh/elixor-lexer";

interface TokenTypes extends TokenStructure {
    leftParenthesis: string;
    rightParenthesis: string;
    quote: string;
    text: string;
}

const tokenTree: TokenTypes = {
    leftParenthesis: "^(",
    rightParenthesis: "^)",
    quote: "^\"",
    text: "^[^\"^(^)]+",
}

const processor = new Lexer<TokenTypes>(tokenTree, `print("Hello World"`);

