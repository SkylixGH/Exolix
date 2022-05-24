package net.skylix.elixor.desktop.unit;

/**
 * A record for settings the corner rounding radius.
 * 
 * @param topLeft The top left corner radius.
 * @param topRight The top right corner radius.
 * @param bottomLeft The bottom left corner radius.
 * @param bottomRight The bottom right corner radius.
 */
public record CornerRadius(int topLeft, int topRight, int bottomLeft, int bottomRight) {
    /**
     * Get the total corner radius.
     * 
     * @return The total corner radius.
     */
    public int getTotal() {
        return topLeft + topRight + bottomLeft + bottomRight;
    }
}
