package com.github.skylixgh.hello;

import java.awt.AWTException;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;

import net.skylix.elixor.desktop.generic.div.Div;
import net.skylix.elixor.desktop.presets.layout.BorderLayout;
import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.GPU;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.renderer.gpu.Renderer;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Location;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    private final Window window;

    public MyApp() {
        window = new Window("Hello World", new Size(1000, 600));

        // Log debug information
        System.out.println("Refresh Rate: " + GPU.getFrameRate());

        final Div el = new Div();

        el.setSize(new Size(100, 200));
        // el.setBackgroundColor(new Color(0, 255, 84, 255));

        for (int i = 0; i < 10; i++) {
            final Div e2 = new Div();

            e2.setSize(new Size(20, 20));
            e2.setBackgroundColor(new Color(255, 0, 0, 255));

            el.add(e2);
        }

        el.setLayoutEngine(new BorderLayout());

        window.setRootElement(el);
        window.run();
    }

    public static void main(String[] args) throws AWTException {
        new MyApp();
    }

    public Window getWindow() {
        return window;
    }
}
 