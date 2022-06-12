interface TokenByIndexStart {
    /**
     * The starting index of the token.
     */
    indexStart: number;
}

interface TokenByIndexEnd {
    /**
     * The ending index of the token.
     */
    indexEnd: number;
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
type TokenLocation = TokenByIndexStart & TokenByIndexEnd & TokenByPositionStart & TokenByPositionEnd;
export default TokenLocation;
