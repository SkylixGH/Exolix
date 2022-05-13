package com.github.skylixgh.hello;

import javax.swing.JLabel;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

public class MyApp {
    public static void main(String[] args) {
        Window win = new Window();
        Div div = new Div();

        JLabel jsb = new JLabel("J = (Compiled in ...");
        jsb.setFont(new Font("Arial", Font.ITALIC, 20));

        div.getSwingComponent().add(jsb);

        win.setRoot(div);
        win.run();
    }
}
