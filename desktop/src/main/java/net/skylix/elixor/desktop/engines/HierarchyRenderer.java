package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.window.Window;

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
     * @param window The window.
     */
    public static void render(Graphics2D g2d, HierarchyTree tree, Window window) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (Component element : tree.getElements()) {
            HierarchyTree contentStore = element.getTree();

            ((Component) element).render(g2d, window, (Component) tree.getOwner());
            g2d.setClip(null);

            if (contentStore.getElements().length > 0) {
                render(g2d, contentStore, window);
            }
        }
    }
}
