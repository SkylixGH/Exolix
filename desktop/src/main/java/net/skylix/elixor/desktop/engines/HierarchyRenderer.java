package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.element.Component;
import net.skylix.elixor.desktop.element.Element;

import java.awt.*;

/**
 * Render a UI tree recursively.
 */
public class HierarchyRenderer {
    /**
     * Render all elements to a graphics output.
     *
     * @param g2d The graphics output.
     * @param tree The tree to render.
     */
    public static void render(Graphics2D g2d, HierarchyTree tree) {
        for (Element element : tree.getElements()) {
            HierarchyTree contentStore = element.getTree();

            if (element instanceof Component) {
                ((Component) element).render(g2d);
            }

            if (contentStore.getElements().length > 0) {
                render(g2d, contentStore);
            }
        }
    }
}
