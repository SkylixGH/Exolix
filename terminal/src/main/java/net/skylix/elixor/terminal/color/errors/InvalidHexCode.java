package net.skylix.elixor.terminal.color.errors;

/**
 * The hex code provided is either the wrong length or is invalid in general.
 */
public class InvalidHexCode extends Exception {
    /**
     * Invalid hex code.
     *
     * @param message Descriptor.
     */
    public InvalidHexCode(String message) {
        super(message);
    }
}
