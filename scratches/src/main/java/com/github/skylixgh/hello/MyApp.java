package com.github.skylixgh.hello;

import java.awt.AWTException;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.renderer.gpu.Renderer;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Size;

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

        A tag = new A();
        A tagg = new A();

        tagg.setSize(new Size(50, 0));

        tag.add(tagg);
        tag.setMinSize(new Size(500, 500));
        tag.setBackgroundColor(new Color(255, 84, 84, 72));
        tagg.setBackgroundColor(new Color(255, 0, 84, 80));

        Canvas canvas = new Canvas() {
            @Override
            public void paintComponent(java.awt.Graphics sg) {
                super.paintComponent(sg);

                Graphics g = new Graphics(sg);
                Renderer.render(g, tag);
            }
        };

        new Thread(() -> {
            boolean a = false;

            while (true) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }

                a = !a;

                canvas.repaint();

                if (a) {
                    tag.setBackgroundColor(new Color(21, 255, 84, 72));
                } else {
                    tag.setBackgroundColor(new Color(21, 200, 84, 80));
                }

                canvas.repaint();
            }
        }).start();

        frame.add(canvas);
        frame.repaint();
    }

    private static class A extends Element {
        private Color bklr = new Color(0, 0, 0, 255);
        private int w = 100;
        @Override
        public Size getSize() {
            return new Size(100, w);
        }

        @Override
        public Size getMaxSize() {
            return new Size(100, w);
        }

        @Override
        public Size getMinSize() {
            return new Size(0, 0);
        }

        @Override
        public Float render(Graphics graphics) {
            return null;
        }

        @Override
        public Size getAutoSize() {
            return null;
        }

        @Override
        public void setSize(Size size) {
            w = size.width();
            
        }

        @Override
        public void setMinSize(Size size) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setMaxSize(Size size) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setBackgroundColor(Color color) {
            bklr = color;
            
        }

        @Override
        public Color getBackgroundColor() {
            // TODO Auto-generated method stub
            return bklr;
        }

        @Override
        public CornerRadius getCornerRadius() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setCornerRadius(CornerRadius cornerRadius) {
            // TODO Auto-generated method stub
            
        }
    }
}
 