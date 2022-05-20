package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used for creating and representing a hierarchy tree.
 */
public class HierarchyTree {
    /**
     * All the sub items.
     */
    private final List<Component> nodes = new ArrayList<>();

    /**
     * The owner of tree.
     */
    private Component owner;

    /**
     * Create a hierachy tree.
     * 
     * @param owner The owner of the tree.
     */
    public HierarchyTree(Component owner) {
        this.owner = owner;
    }

    /**
     * Add an element to the hierarchy tree.
     *
     * @param element The element to add.
     */
    public void add(Component element) {
        nodes.add(element);
    }

    /**
     * Get all the elements.
     *
     * @return All the elements.
     */
    public Component[] getElements() {
        return nodes.toArray(new Component[0]);
    }
    
    /**
     * Get the tree owner.
     * 
     * @return The owner of the tree.
     */
    public Component getOwner() {
        return owner;
    }
}
