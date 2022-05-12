package com.github.skylixgh.hello;

import javax.swing.JLabel;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    public static void main(String[] args) {
        Window win = new Window();
        Div div = new Div();

        JLabel jsb = new JLabel("JS = Bitc- (Compiled in ...");
        jsb.setFont(new Font                  nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn   nnn                                                                       nn     n                                                                                    nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn())


        div.setWidth(100);
        div.setHeight(100);

        div.getMargin().setBottom(100);
        div.getBorderRadius().setTopLeft(10);

        div.getSwingComponent().add(jsb);

        win.add(div);
        win.run();
    }
}
