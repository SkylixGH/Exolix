import { Buffer } from "node:buffer";
import Token from "../token/Token";
import TokenStructure from "../token/TokenStructure";

/**
 * A lexer is a programmatic tool which analyzes a string, once finished, it can
 * manipulate the tokens, return them, and more.
 */
export default class Lexer<Structure extends TokenStructure> {
    /**
     * The input data of the lexer.
     */
    #input: string;

    /**
     * All the analyzed tokens from the lexical analyzer.
     */
    #tokens: Token<Structure>[] = [];

    /**
     * The token tree.
     */
    #tree: Structure;

    /**
     * Create a new lexical analyzer.
     *
     * @param tree The token regexp structure tree.
     * @param input The input data for the lexer.
     */
    public constructor(tree: Structure, input: string | Buffer) {
        this.#input = input.toString();
        this.#tree = tree;
    }
}
