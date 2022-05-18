package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.component.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

// import InputEvent
// import KeyEvent


public class MyApp {
    private static boolean full;
    public static void main(String[] args) {
        final Window window = new Window("Elixor");
        final HierarchyTree tree = window.getHierarchyTree();

        // Components
        final Div root = new Div();

        // Add elements
        tree.add(root);

        root.setBackgroundColor(Color.BLACK);
        root.setSize(new Size(200, 200));

        // Set window information
        window.setSize(new Size(1200, 600));

        window.run();
    }
}
 