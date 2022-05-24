package net.skylix.elixor.desktop.renderer.gpu;

import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.renderer.color.Color;

import java.awt.geom.Path2D;

/**
 * A graphics controller object. 
 */
public class Graphics {
    /**
     * The basic Java graphics object.
     */
    private final java.awt.Graphics2D javaGraphics;

    /**
     * Create a new graphics controller from a basic Java graphics object.
     * 
     * @param javaGraphics The Java graphics object.
     */
    public Graphics(java.awt.Graphics javaGraphics) {
        this.javaGraphics = (java.awt.Graphics2D) javaGraphics;

        final java.awt.Graphics2D g = (java.awt.Graphics2D) javaGraphics;

        g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /**
     * Draw a rectangle.
     * 
     * @param x The x coordinate of the rectangle.
     * @param y The y coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     * @param strokeWidth The width of the stroke.
     * @param strokeColor The color of the stroke.
     * 
     * @return the object shape.
     */
    public Path2D.Float drawRect(int x, int y, int width, int height, CornerRadius cornerRadius, Color color, int strokeWidth, Color strokeColor) {
        final Path2D.Float shape = new Path2D.Float();

        if (cornerRadius.getTotal() > 0) {
            int tl = cornerRadius.topLeft();
            int tr = cornerRadius.topRight();
            int bl = cornerRadius.bottomLeft();
            int br = cornerRadius.bottomRight();

            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            if (tl > halfHeight) {
                tl = halfHeight;
            } else if (tl > halfWidth) {
                tl = halfWidth;
            }

            if (tr > halfHeight) {
                tr = halfHeight;
            } else if (tr > halfWidth) {
                tr = halfWidth;
            }

            if (bl > halfHeight) {
                bl = halfHeight;
            } else if (bl > halfWidth) {
                bl = halfWidth;
            }

            if (br > halfHeight) {
                br = halfHeight;
            } else if (br > halfWidth) {
                br = halfWidth;
            }

            shape.moveTo(x + tl, y);
            shape.lineTo(x + width - tr, y);
            shape.quadTo(x + width, y, x + width, y + tr);
            shape.lineTo(x + width, y + height - br);
            shape.quadTo(x + width, y + height, x + width - br, y + height);
            shape.lineTo(x + bl, y + height);
            shape.quadTo(x, y + height, x, y + height - bl);
            shape.lineTo(x, y + tl);
            shape.quadTo(x, y, x + tl, y);
        } else {
            shape.moveTo(x, y);
            shape.lineTo(x + width, y);
            shape.lineTo(x + width, y + height);
            shape.lineTo(x, y + height);
            shape.lineTo(x, y);
        }

        shape.closePath();
        
        javaGraphics.setColor(color.getJavaColor());
        javaGraphics.fill(shape);

        if (strokeWidth > 0) {
            javaGraphics.setColor(strokeColor.getJavaColor());
            javaGraphics.setStroke(new java.awt.BasicStroke(strokeWidth));
            javaGraphics.draw(shape);
        }

        return shape;
    }
}
