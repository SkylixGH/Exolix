package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    public static void main(String[] args) {
        Window win = new Window();
        Div div = new Div();

        div.getPadding().setTop(100);
        div.getPadding().setBottom(100);
        div.getPadding().setLeft(100);
        div.getPadding().setRight(100);

        win.add(div);
        win.run();
    }
}
