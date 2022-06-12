import { Lexer, Token } from "../Main";
import TokenLocation from "./TokenLocation";

/**
 * This class is used to manipulate a list of tokens.
 */
export default class TokenUtil<TokenTypes extends Object> {
    /**
     * The lexical analyzer.
     */
    readonly lexer: Lexer<TokenTypes>

    /**
     * The tokens that the lexer analyzed.
     */
    readonly tokens: Token<TokenTypes>[];

    /**
     * Create a token analyzer from a lexer.
     * 
     * @param lexer The lexer that analyzed the tokens.
     */
    constructor(lexer: Lexer<TokenTypes>) {
        this.lexer = lexer;
        this.tokens = lexer.tokens;
    }

    /**
     * Find a token in the lexer result by its location.
     * 
     * @param location The location of the token.
     * @returns The token result, otherwise undefined.
     */
    public getToken(location: TokenLocation): Token<TokenTypes> | undefined {
        return this.tokens[location.indexStart];
    }

    /**
     * Find first tokens starting from a given location, all the way until a condition is met.
     * 
     * @param start The starting token location.
     * @param condition The condition executor, once met true, that token will be returned.
     */
    public findUntil(start: TokenLocation, condition: (token: Token<TokenTypes>) => boolean): Token<TokenTypes> | undefined {
        let index = start.indexStart;
        let line = start.lineStart;
        let column = start.columnStart;

        while (index < this.tokens.length) {
            const token = this.tokens[index];

            if (condition(token)) {
                return token;
            }

            index++;
            column++;

            if (token.value === this.lexer.newLine) {
                line++;
                column = 0;
            }

            if (index === this.tokens.length) {
                return undefined;
            }
        }
    }
}