package net.skylix.elixor.desktop.element;

import net.skylix.elixor.desktop.engines.HierarchyTree;

/**
 * An element, which can be added to a window and is required for rendering and creating more specific elements.
 */
public class Element {
    /**
     * Child nodes.
     */
    private final HierarchyTree tree = new HierarchyTree();

    /**
     * Add an element to the 3D tree.
     *
     * @param element The element to add.
     */
    public void add(Element element) {
        tree.add(element);
    }

    /**
     * Get hierarchy tree.
     *
     * @return Hierarchy tree.
     */
    public HierarchyTree getTree() {
        return tree;
    }
}
