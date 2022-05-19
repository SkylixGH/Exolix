package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.component.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

// import InputEvent
// import KeyEvent


public class MyApp {
    public static void main(String[] args) {
        final Window window = new Window("Elixor");

        configureWindow(window);

        final Div div = new Div();

        div.setSize(new Size(window.getWidth() - 16, window.getHeight() - 39));
        div.setBackgroundColor(new Color(255, 255, 255));
        div.setPosition(new Position(8, 24));

        for (int i = 0; i < 20; i++) {
            final Div div2 = new Div();

            div2.setSize(new Size(10, 10));
            div2.setBackgroundColor(new Color(83, 150, 255));

            div.add(div2);
        }

        window.add(div);
        window.run();
    }

    private static void configureWindow(Window win) {
        win.setSize(new Size(800, 500));
    }
}
 