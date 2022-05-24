package net.skylix.elixor.desktop.renderer.color;

/**
 * A color.
 * 
 * @param red The red component of the color.
 * @param green The green component of the color.
 * @param blue The blue component of the color.
 * @param alpha The alpha component of the color.
 */
public record Color(int red, int green, int blue, int alpha) {
    /**
     * The Java AWT color.
     */
    private static java.awt.Color javaColor;

    /**
     * Get the color as a hexadecimal string.
     * 
     * @return The color as a hexadecimal string.
     */
    public String toHex() {
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    /**
     * Get the color as a string.
     * 
     * @return The color as a string.
     */
    public String toString() {
        return String.format("(%d, %d, %d, %d)", red, green, blue, alpha);
    }

    /**
     * Get the Java AWT color.
     * 
     * @return The Java AWT color.
     */
    public java.awt.Color getJavaColor() {
        if (javaColor == null) {
            javaColor = new java.awt.Color(red, green, blue, alpha);
        }

        return javaColor;
    }
}
