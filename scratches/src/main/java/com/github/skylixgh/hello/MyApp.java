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

        window.setRootElement(new Element() {
            @Override
            public Path2D.Float render(Graphics gx) {
                gx.drawRect(0, 0, 100, 100, new CornerRadius(0, 0, 0, 0), new Color(255, 63, 83, 255), 0, null);
                return null;
            }
        });
        window.run();
    }

    public static void main(String[] args) throws AWTException {
        new MyApp();
    }

    public Window getWindow() {
        return window;
    }
}
 