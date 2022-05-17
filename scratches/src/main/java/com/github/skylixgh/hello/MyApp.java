package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.element.div.Div;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    public static void main(String[] args) {
        final Window game = new Window("Elixor");
        final Div root = new Div();

        game.add(root);
        game.setSize(new Size(1000, 700));

        game.run();
    }
}
