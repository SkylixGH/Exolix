package net.skylix.elixor.desktop.window;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

import net.skylix.elixor.desktop.elements.Div;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Margin;
import net.skylix.elixor.desktop.unit.Padding;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A class for creating a desktop window on a desktop computer.
 */
public class Window {
    /**
     * The title for the window.
     */
    private String title;

    /**
     * The javax swing window.
     */
    private final JFrame window;

    /**
     * The root panel of the window.
     */
    private final RenderingJComponent panel;

    /**
     * The window dimensions.
     */
    private Dimension size = new Dimension(800, 500);

    /**
     * Create a window with a title.
     * @param title The title of the window.
     */
    public Window(String title) {
        this.title = title;

        window = new JFrame(title);

        // Modify the panel's paint method, we need to do this so that the framework can actually customise the way things are drawn.
        panel = new RenderingJComponent();

        // Customise the panel.
        panel.setBackground(new Color(243, 243, 243));

        // Configure the window metrics and other properties.
        window.setSize(size);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel to the window.
        window.setContentPane(panel);

        panel.render();
    }

    /**
     * Create a window with a blank title.
     */
    public Window() {
        this("");
    }

    /**
     * Update the title of this window.
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
        window.setTitle(title);
    }

    /**
     * Set the window's size.
     * @param width The width of the window.
     * @param height The height of the window.
     */
    public void setSize(int width, int height) {
        size = new Dimension(width, height);
        window.setSize(size);
    }

    /**
     * Get the window handle for when the window is running on the Windows platform.
     * @return The window handle if the window is running on the Windows platform.
     */
    private WinDef.HWND getWindowHandle() {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) return null;

        WinDef.HWND hWnd = new WinDef.HWND();
        hWnd.setPointer(Native.getComponentPointer(window));

        return hWnd;
    }

    /**
     * Run the window and allow it to be visible.
     * If the windw is already visible, nothing will happen.
     */
    public void run() {
        if (window.isVisible()) return;
        window.setVisible(true);
    }

    /**
     * Check to see if the window is running.
     */
    public boolean isRunning() {
        return window.isVisible();
    }

    /**
     * Get the window's title.
     * @return The window's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Add an element.
     * @param element The element to add.
     */
    public void add(Div element) {
        panel.add(element);
    }

    /**
     * Remove an element.
     * @param element The element to remove.
     */
    public void remove(Div element) {
        panel.remove(element);
    }
}

/**
 * The custom rendering panel for the window.
 */
class RenderingJComponent extends JComponent {
    /**
     * All elements.
     */
    private final ArrayList<Div> elements = new ArrayList<>();

    /**
     * The custom rendering method.
     * @param g The graphics object to draw on.
     */
    @Override
    public void paintComponent(Graphics g3d) {
        super.paintComponent(g3d);

        // Create the 2D graphics object.
        Graphics2D g = (Graphics2D) g3d;

        // Draw all top level components
        for (Div element : elements) {
            Padding padding = element.getPadding();
            Margin margin = element.getMargin();
            BorderRadius borderRadius = element.getBorderRadius();

            final float x = padding.getLeft() + margin.getLeft();
            final float y = padding.getTop() + margin.getTop();

            g.setColor(Color.BLACK);
            element.render(g);
        }
    }

    /**
     * Re render the current panel contents and run the recalculations of the components.
     */
    public void render() {
        repaint();
    }

    /**
     * Add an element.
     * @param element The element to add.
     */
    public void add(Div element) {
        elements.add(element);
        render();
    }

    /**
     * Remove an element.
     * @param element The element to remove.
     */
    public void remove(Div element) {
        elements.remove(element);
        render();
    }
 }
