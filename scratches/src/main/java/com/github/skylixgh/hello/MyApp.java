package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyApp {
    public static void main(String[] args) {
        final Window game = new Window("Elixor");

        game.setSize(new Size(1000, 700));

        game.run();

        // set the window size to 800x600 after 1000ms
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.getSize().setWidth(800);
            game.getSize().setHeight(600);
        });

        t.start();
    }
}
