interface TokenByCharIndexStart {
    /**
     * The starting char index of the token.
     */
    indexCharStart: number;
}

interface TokenByCharIndexEnd {
    /**
     * The ending char index of the token.
     */
    indexCharEnd: number;
}

interface TokenByPositionStart {
    /**
     * The starting line of the token.
     */
    lineStart: number;

    /**
     * The starting column of the token.
     */
    columnStart: number;
}

interface TokenByPositionEnd {
    /**
     * The ending line of the token.
     */
    lineEnd: number;

    /**
     * The ending column of the token.
     */
    columnEnd: number;
}

/**
 * An interface that identifies the location of a token.
 */
type TokenLocation = TokenByCharIndexStart | TokenByCharIndexEnd | TokenByPositionStart | TokenByPositionEnd;
export default TokenLocation;
