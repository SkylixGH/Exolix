package net.skylix.elixor.desktop.engines.layout;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;

/**
 * A layout that places components in a border row.
 */
public class BorderLayout extends Layout {
    /**
     * Process the layout information.
     * 
     * @param tree The hierarchy tree to process.
     * @param parent The parent element of the tree.
     */
    @Override
    public void process(HierarchyTree tree, Component parent) {
        if (parent != null) {
            parent.setX(39);
            parent.setY(39);
        }

        for (Component item : tree.getElements()) {
            item.setX(60);
            item.setY(60);
        }

        System.out.println("BorderLayout Items = " + tree.getElements().length + " Parent = " + parent);
    }
}
