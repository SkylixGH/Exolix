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
        const anyLocation = location as any;
        let result: Token<TokenTypes> | undefined;

        if (typeof anyLocation.indexCharStart !== "undefined") {
            result = this.tokens.find(token => {
                return token.start === anyLocation.indexCharStart;
            });
        } else if (typeof anyLocation.lineStart !== "undefined" && typeof anyLocation.columnStart !== "undefined") {
            result = this.tokens.find(token => {
                return token.lineStart === anyLocation.lineStart && token.columnStart === anyLocation.columnStart;
            });
        } else if (typeof anyLocation.lineEnd !== "undefined" && typeof anyLocation.columnEnd !== "undefined") {
            result = this.tokens.find(token => {
                return token.lineEnd === anyLocation.lineEnd && token.columnEnd === anyLocation.columnEnd;
            });
        } else if (typeof anyLocation.indexCharEnd !== "undefined") {
            result = this.tokens.find(token => {
                return token.end === anyLocation.indexCharEnd;
            });
        }

        return result;
    }

    /**
     * Find first tokens starting from a given location, all the way until a condition is met.
     * 
     * @param start The starting token location.
     * @param condition The condition executor, once met true, that token will be returned.
     */
    public findUntil(start: TokenLocation, condition: (token: Token<TokenTypes>) => boolean): Token<TokenTypes> | undefined {
        const startToken = this.getToken(start)!;

        if (startToken === undefined)
            return undefined;

        let index = startToken.index;

        while (index < this.tokens.length) {
            const token = this.tokens[index];

            if (condition(token)) {
                return token;
            }

            index++;
            
            if (index === this.tokens.length) {
                return undefined;
            }
        }
    }

    /**
     * Get a token that is somewhere after a specific starting location.
     * 
     * @param start The starting token location.
     * @param type The token type to check.
     * @return The matched token, otherwise undefined.
     */
    public getAfter(start: TokenLocation, type: keyof TokenTypes): Token<TokenTypes> | undefined {
        const startToken = this.getToken(start)!;

        if (startToken === undefined)
            return undefined;

        let index = startToken.index + 1;

        while (index < this.tokens.length) {
            const token = this.tokens[index];

            if (token.type === type) {
                return token;
            }

            index++;
            
            if (index === this.tokens.length) {
                return undefined;
            }
        }

        return undefined;
    }
}