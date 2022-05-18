package net.skylix.elixor.desktop.engines;

import net.skylix.elixor.desktop.component.Component;

/**
 * This asbtract class contains methods that are common to all layouts.
 */
public abstract class Layout {
    /**
     * Process layout details.
     * 
     * @param tree The hierarchy tree to process.
     * @parm parent The parent element of the tree.
     */
    public abstract void process(HierarchyTree tree, Component parent);
}
