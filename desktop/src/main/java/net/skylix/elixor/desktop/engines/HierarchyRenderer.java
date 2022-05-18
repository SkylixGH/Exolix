package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;
import java.util.ArrayList;

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
        ArrayList<Component> rendered = new ArrayList<>();
        recursiveRender(g2d, tree, window, rendered);
    }

    /**
     * Recursivly render all elements to a graphics output.
     *
     * @param g2d The graphics output.
     * @param tree The tree to render.
     * @param window The window.
     * @param rendered The list of already rendered elements.
     */
    private static void recursiveRender(Graphics2D g2d, HierarchyTree tree, Window window, ArrayList<Component> rendered) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (Component element : tree.getElements()) {
            HierarchyTree contentStore = element.getTree();

            ((Component) element).render(g2d, window, (Component) tree.getOwner());
            g2d.setClip(null);

            rendered.add(element);

            if (contentStore.getElements().length > 0) {
                recursiveRender(g2d, contentStore, window, rendered);
            }
        }
    }
}
