package net.skylix.elixor.desktop.unit;

/**
 * This record contains the width and height of a certain item.
 */
public record Size(int width, int height) {
    /**
     * Get the total sum of both the width and height.
     * 
     * @return The total sum of both the width and height.
     */
    public int getTotal() {
        return width + height;
    }
}
