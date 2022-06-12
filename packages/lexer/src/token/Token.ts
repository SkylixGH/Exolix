/**
 * A token is a specific pinpoint relative to the input string.
 */
export default interface Token<Structure extends Object> {
    /**
     * The starting line of the token.
     */
    lineStart: number;

    /**
     * The ending line of the token.
     */
    lineEnd: number;

    /**
     * The starting column of the token.
     */
    columnStart: number;

    /**
     * The ending column of the token.
     */
    columnEnd: number;

    /**
     * The token type.
     */
    type: keyof Structure;

    /**
     * The starting index of the token.
     */
    start: number;

    /**
     * The ending index of the token.
     */
    end: number;

    /**
     * The value match of the token.
     */
    value: string;
}
