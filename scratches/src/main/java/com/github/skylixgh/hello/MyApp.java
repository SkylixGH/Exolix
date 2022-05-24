package com.github.skylixgh.hello;

import java.awt.AWTException;
import java.awt.geom.Path2D;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.unit.CornerRadius;

public class MyApp {
    public static void main(String[] args) throws AWTException {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(256, 256);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Canvas canvas = new Canvas() {
            @Override
            public void paintComponent(java.awt.Graphics sg) {
                super.paintComponent(sg);

                Graphics g = new Graphics(sg);

                Path2D.Float s = g.drawRect(10, 10, 50, 50, new CornerRadius(50, 50, 50, 50), new Color(255, 83, 21, 255), 0, new Color(0, 0, 0, 0));
            }
        };

        frame.add(canvas);
        frame.repaint();
    }
}
 