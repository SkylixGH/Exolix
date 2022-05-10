package net.skylix.elixor.desktop.window;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;
import java.awt.*;

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
    private final JComponent panel;

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
        panel = new JComponent() {{
            @Override
            public void paintComponent(Graphics g) {

            }
        }};

        // Customise the panel.
        panel.setBackground(new Color(243, 243, 243));

        // Configure the window metrics and other properties.
        window.setSize(size);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panel to the window.
        window.setContentPane(panel);
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
     */
    public void run() {
        if (window.isVisible()) return;
        window.setVisible(true);
    }
}
