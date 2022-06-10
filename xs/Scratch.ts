import {Lexer, Token, TokenStructure} from "@skylixgh/elixor-lexer";

interface TokenTypes extends TokenStructure {
    leftParenthesis: RegExp;
    rightParenthesis: RegExp;
    quote: RegExp;
    text: RegExp;
}

const tokenTree: TokenTypes = {
    leftParenthesis: /^\(/,
    rightParenthesis: /^\)/,
    quote: /^"/,
    space: /^\s+/,
    text: /^[^"^()]+/,
}

const processor = new Lexer<TokenTypes>(tokenTree, `print("Hello World")`);

const tokens = processor.tokens;
const input = processor.input;

let index = 0;
const max = tokens.length - 1;
const delay = 1000;

const go = () => {
    // This will render the initial input, but it will invert the actual match text
    // Like this
    // 0: [Hello] Nice World
    // 1: Hello [Nice] World
    // 2: Hello Nice [World]

    const color = "\x1b[2;37;41m";
    const reset = "\x1b[0m";

    const current = `${color}${tokens[index].value}${reset}`;

    const beforeCurrentTokens: Token<TokenTypes>[] = tokens.slice(0, index);
    const afterCurrentTokens: Token<TokenTypes>[] = tokens.slice(index + 1);

    const beforeValue = beforeCurrentTokens.map(token => token.value).join("");
    const afterValue = afterCurrentTokens.map(token => token.value).join("");

    console.clear();
    console.log(`${beforeValue}${current}${afterValue}`);

    index++;

    if (index > max) {
        return;
    }

    setTimeout(go, delay);
}

go();
