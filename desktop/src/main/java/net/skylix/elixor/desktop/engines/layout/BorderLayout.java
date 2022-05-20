package net.skylix.elixor.desktop.engines.layout;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.unit.Position;

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
        int lastX = parent != null ? parent.getXPosition() : 0;
        int lastY = parent != null ? parent.getYPosition() : 0;

        for (Component node : tree.getElements()) {
            if (node.getXPosition() == 0) {
                node.setPosition(new Position(lastX, lastY));
            }

            lastX = node.getXPosition() + node.getWidth() + 1;
        }
    }
}
