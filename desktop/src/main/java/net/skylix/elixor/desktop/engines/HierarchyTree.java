package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.element.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used for creating and representing a hierarchy tree.
 */
public class HierarchyTree {
    /**
     * All the sub items.
     */
    private final List<Element> nodes = new ArrayList<>();

    /**
     * Add an element to the hierarchy tree.
     *
     * @param element The element to add.
     */
    public void add(Element element) {
        nodes.add(element);
    }

    /**
     * Get all the elements.
     *
     * @return All the elements.
     */
    public Element[] getElements() {
        return nodes.toArray(new Element[0]);
    }
}
