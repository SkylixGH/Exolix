package net.skylix.elixor.terminal.color.errors;

/**
 * The RGB values are invalid, some of them may be out of range.
 */
public class InvalidRGBValues extends Exception {
    /**
     * Invalid hex code.
     *
     * @param message Descriptor.
     */
    public InvalidRGBValues(String message) {
        super(message);
    }
}
