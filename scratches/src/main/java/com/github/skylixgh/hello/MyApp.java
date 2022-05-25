package com.github.skylixgh.hello;

import java.awt.AWTException;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import net.skylix.elixor.desktop.presets.layout.BorderLayout;
import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.renderer.gpu.Renderer;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Location;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    private static Canvas canvas;
    public static void main(String[] args) throws AWTException {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(556, 656);
        frame.setLocationRelativeTo(null);

        A tag = new A();
        A tagg = new A();

        tagg.setSize(new Size(50, 50));
        tag.setSize(new Size(20, 50));
//        tagg.setMinSize();

        tag.add(tagg);
        tag.setMinSize(new Size(20, 100));
        tag.setMaxSize(new Size(20, 100));
        tag.setBackgroundColor(new Color(24, 84, 255, 72));

        tagg.setMinSize(new Size(50, 50));
        tagg.setMaxSize(new Size(500, 500));
        tagg.setBackgroundColor(new Color(255, 0, 255, 80));

        Window w = new Window() {
            @Override
            public void refresh() {
                frame.repaint();
            }
        };

        canvas = new Canvas() {
            @Override
            public void paintComponent(java.awt.Graphics sg) {
                super.paintComponent(sg);

                Graphics g = new Graphics(sg);
                Renderer.render(g, tag, w);
            }
        };

        new Thread(() -> {
            boolean a = false;

            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }

                a = !a;

                if (a) {
                    tagg.setBackgroundColor(new Color(21, 255, 84, 0));
                } else {
                    tagg.setBackgroundColor(new Color(21, 200, 84, 80));
                }
            }
        }).start();

        tag.setPosition(new Location(0, 0));
        tagg.setPosition(new Location(0, 0));
        tagg.setSize(new Size(50, 50));

        frame.add(canvas);
        frame.repaint();

        frame.setVisible(true);
    }

    private static class A extends Element {
        private Color bklr = new Color(0, 0, 0, 255);
        private Window pw;
        private Path2D.Float shp;
        private int w = 100;

        @Override
        public Float render(Graphics graphics) {
            final Size size = getSize() != null ? getSize() : getAutoSize();
            final Size maxSize = getMaxSize();
            final Size minSize = getMinSize();

            int width = size.width();
            int height = size.height();

            if (width > maxSize.width()) {
                width = maxSize.width();
            } else if (width < minSize.width()) {
                width = minSize.width();
            }

            if (height > maxSize.height()) {
                height = maxSize.height();
            } else if (height < minSize.height()) {
                height = minSize.height();
            }

            Path2D.Float e=graphics.drawRect(
                    getPosition().x(),
                    getPosition().y(),
                    width,
                    height,
                    getCornerRadius(),
                    getBackgroundColor(),
                    0,
                    null
            );

            shp = e;
            return e;
        }
    }
}
 