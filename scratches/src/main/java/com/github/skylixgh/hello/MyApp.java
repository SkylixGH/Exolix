package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.element.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
// import InputEvent
// import KeyEvent
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.InputEvent;

public class MyApp {
    private static boolean full;
    public static void main(String[] args) {
        final Window game = new Window("Elixor");
        final HierarchyTree tree = game.getHierarchyTree();
        final Div root = new Div();
        final Div box = new Div();

        root.setWidth(100);
        root.setBackgroundColor(new Color(0, 0, 0, 15));
        box.setWidth(30);
        box.setBackgroundColor(new Color(0, 0, 0));
        game.setSize(new Size(1000, 700));
        game.add(root);
        root.add(box);
        game.run();

        root.disableEdgeClipping();

        root.setSize(new Size(100, 100));

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Div box2 = new Div();
                box.setWidth(100);

                // set 100% random color
                box2.setBackgroundColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255), 100));

                box.setHeight(game.getMouseX() / 10);
            }
        }).start();
    }
}
 