package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.component.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

// import InputEvent
// import KeyEvent


public class MyApp {
    public static void main(String[] args) {
        final Window window = new Window("Elixor");
        final HierarchyTree tree = window.getHierarchyTree();

        // Components
        final Div root = new Div();
        final Div subItem = new Div();

        // Add elements
        tree.add(root);
        root.add(subItem);

        subItem.setSize(new Size(50, 50));
        subItem.setBackgroundColor(Color.BLACK);

        root.setBackgroundColor(Color.LIGHT_GRAY);
        root.setSize(new Size(200, 200));
        root.getBorderRadius().setTopRight(30);

        // Set window information
        window.setSize(new Size(1200, 600));

        window.run();
    }
}
 