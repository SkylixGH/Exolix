package net.skylix.elixor.terminal.color.errors;

/**
 * The alpha value for the RGB color is invalid, it may be out of range.
 */
public class InvalidRGBAlpha extends Exception {
    /**
     * Invalid hex code.
     *
     * @param message Descriptor.
     */
    public InvalidRGBAlpha(String message) {
        super(message);
    }
}
