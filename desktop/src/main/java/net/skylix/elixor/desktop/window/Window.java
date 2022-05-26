package net.skylix.elixor.desktop.window;

import javax.swing.JFrame;

import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.GPU;
import net.skylix.elixor.desktop.renderer.gpu.Renderer;
import net.skylix.elixor.desktop.unit.Location;
import net.skylix.elixor.desktop.unit.Size;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * A cross-platform desktop platform window.
 */
public class Window {
    /**
     * The title of the window.
     */
    private String title;

    /**
     * The size of the window.
     */
    private Size size;

    /**
     * The location of the window.
     */
    private Location location;

    /**
     * The Java swing window.
     */
    private final JFrame swingWindow;

    /**
     * The refresh thread.
     */
    private Thread refreshThread;

    /**
     * The current root element.
     */
    private Element rootElement;

    /**
     * Create a new window.
     * 
     * @param title The title of the window.
     * @param size The size of the window.
     * @param location The location position of the window.
     */
    public Window(final String title, final Size size, final Location location) {
        this.title = title;
        this.size = size;
        this.location = location;

        swingWindow = new JFrame(title);

        swingWindow.setLocation(location.x(), location.y());
        swingWindow.setSize(size.width(), size.height());

        final Window window = this;

        final Canvas viewport = new Canvas() {
            @Override
            public void paintComponent(Graphics g3d) {
                super.paintComponent(g3d);

                if (rootElement == null) return;

                final net.skylix.elixor.desktop.renderer.gpu.Graphics g = new net.skylix.elixor.desktop.renderer.gpu.Graphics((Graphics2D) g3d);
                Renderer.render(g, rootElement, window);
            }
        };

        swingWindow.setContentPane(viewport);
    }

    /**
     * Create a new window.
     * 
     * @param title The title of the window.
     * @param size The size information for the window.
     */
    public Window(final String title, final Size size) {
        this(title, size, new Location(0, 0));
    }

    /**
     * Create a new window.
     * 
     * @param title The title of the window.
     */
    public Window(final String title) {
        this(title, new Size(0, 0));
    }

    /**
     * Create a new window.
     */
    public Window() {
        this("");
    }

    /**
     * Refresh the window.
     */
    public void refresh() {
        swingWindow.getRootPane().repaint();
    }

    /**
     * Get the title of the window.
     * 
     * @return The title of the window.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the size of the window.
     * 
     * @return The size of the window.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Get the location of the window.
     * 
     * @return The location of the window.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Run the window to show it on the screen.
     */
    public void run() {
        if (swingWindow.isVisible()) return;

        refreshThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000 / GPU.getFrameRate());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                refresh();
            }
        });

        refreshThread.start();

        swingWindow.setVisible(true);
    }

    /**
     * Check to see if the window is running.
     * 
     * @return True if the window is running, false otherwise.
     */
    public boolean isRunning() {
        return swingWindow.isVisible();
    }

    /**
     * Stop the window.
     */
    public void stop() {
        if (!swingWindow.isVisible() || !swingWindow.isActive()) return;

        refreshThread.interrupt();
        swingWindow.dispose();
    }

    /**
     * Set the root element.
     * 
     * @param element The root element.
     */
    public void setRootElement(Element element) {
        rootElement = element;
    }

    /**
     * Get the root element.
     * 
     * @return The root element.
     */
    public Element getRootElement() {
        return rootElement;
    }
}
