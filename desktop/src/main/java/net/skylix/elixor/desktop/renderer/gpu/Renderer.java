package net.skylix.elixor.desktop.renderer.gpu;

import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Size;

/**
 * This class accepts a root element and recursivly renders
 * it to a graphics renderer.
 */
public class Renderer {
    /**
     * This is a recursive function, it will
     * go through all of the elements slowly 
     * and then render them one by one to the
     * display canvas.
     * 
     * @param gx The graphics rendering object.
     * @param root The root element.
     */
    public static void render(Graphics gx, Element root) {
        final Size size = root.getSize() != null ? root.getSize() : root.getAutoSize();
        final Size maxSize = root.getMaxSize();
        final Size minSize = root.getMinSize();

        int width = size.width();
        int height = size.height();

        if (width > maxSize.width()) {
            width = maxSize.width();
        } else if (width < minSize.width()) {
            width = minSize.width();
        }

        if (height > maxSize.height()) {
            height = maxSize.height();
        } else if (height < minSize.height()) {
            height = minSize.height();
        }

        gx.drawRect(
            0, 
            0, 
            width,
            height,
            new CornerRadius(10, 0, 10, 0), 
            root.getBackgroundColor(), 
            0, 
            null
        );

        for (Element e : root.getNodes()) {
            e.setParent(root);
            render(gx, e);
        }
    }    
}

 