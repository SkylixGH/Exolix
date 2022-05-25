package net.skylix.elixor.desktop.renderer.color;

/**
 * A color.
 * 
 * @param red The red component of the color.
 * @param green The green component of the color.
 * @param blue The blue component of the color.
 * @param alpha The alpha component of the color.
 */
public class Color {
    /**
     * The Java AWT color.
     */
    private final java.awt.Color javaColor;

    /**
     * A transparent static constant color.
     */
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Create a new color instance.
     */
    public Color(int red, int green, int blue, int alpha) {
        this.javaColor = new java.awt.Color(red, green, blue, alpha);
    }

    /**
     * Get the color as a hexadecimal string.
     * 
     * @return The color as a hexadecimal string.
     */   
    public String toHex() {
        return "#" + Integer.toHexString(this.javaColor.getRGB()).substring(2);
    }

    /**
     * Get the color as a string.
     * 
     * @return The color as a string.
     */
    public String toString() {
        return this.javaColor.toString();
    }

    /**
     * Get the Java AWT color.
     * 
     * @return The Java AWT color.
     */
    public java.awt.Color getJavaColor() {
        return javaColor;
    }

    /**
     * Get the red component of the color.
     * 
     * @return The red component of the color.
     */
    public int getRed() {
        return javaColor.getRed();
    }

    /**
     * Get the green component of the color.
     * 
     * @return The green component of the color.
     */
    public int getGreen() {
        return javaColor.getGreen();
    }

    /**
     * Get the blue component of the color.
     * 
     * @return The blue component of the color.
     */
    public int getBlue() {
        return javaColor.getBlue();
    }

    /**
     * Get the alpha component of the color.
     * 
     * @return The alpha component of the color.
     */
    public int getAlpha() {
        return javaColor.getAlpha();
    }
}
