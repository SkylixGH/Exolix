package net.skylix.elixor.desktop.renderer.gpu;

import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

/**
 * This class accepts a root element and recursively renders
 * it to a graphics renderer.
 */
public class Renderer {
    /**
     * This is a recursive function, it will
     * go through all the elements slowly
     * and then render them one by one to the
     * display canvas.
     * 
     * @param gx The graphics rendering object.
     * @param root The root element.
     * @param rootWindow The root window.
     */
    public static void render(Graphics gx, Element root, Window rootWindow) {
        root.setParent(root);
        root.setParentWindow(rootWindow);

        root.processLayout();
        root.render(gx);

        for (Element e : root.getNodes()) {
            e.setParent(root);
            e.setParentWindow(rootWindow);

            render(gx, e, rootWindow);
        }
    }    
}

 