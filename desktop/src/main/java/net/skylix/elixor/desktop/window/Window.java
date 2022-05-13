package net.skylix.elixor.desktop.window;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

import net.skylix.elixor.desktop.elements.Div;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        window.add(panel);

        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.repaint();
            }
        });

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
     * If the window is already visible, nothing will happen.
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
     * Set the root element.
     * @param element The element to set as the root.
     */
    public void setRoot(Div element) {
        panel.setContentPane(element);
    }
}

/**
 * The custom rendering panel for the window.
 */
class RenderingJComponent extends JComponent {
    /**
     * All elements.
     */
    private Div contentPane;

    /**
     * The window
     */
    private JFrame window = null;

    /**
     * Create the internal panel.
     */
    public RenderingJComponent() {
        super();
    }

    /**
     * The custom rendering method.
     * @param g3d The graphics object to draw on.
     */
    @Override
    public void paintComponent(Graphics g3d) {
        super.paintComponent(g3d);

        if (window == null) {
            window = (JFrame) SwingUtilities.getWindowAncestor(this);
        }

        // Create the 2D graphics object.
        Graphics2D g = (Graphics2D) g3d;

        // Draw content
        if (contentPane != null) {
            contentPane.setWidth(getWidth());
            contentPane.setHeight(getHeight());

            contentPane.getMargin().setLeft(10);
            contentPane.getMargin().setRight(10);
            contentPane.getMargin().setTop(10);
            contentPane.getMargin().setBottom(10);

            contentPane.getBorderRadius().setArchHeight(25);
            contentPane.getBorderRadius().setArchWidth(25);

            contentPane.setBorderStrokeWidth(3);

            contentPane.render(g);
        }

        g.dispose();
    }

    /**
     * Re-render the current panel contents and run the recalculations of the components.
     */
    public void render() {
        repaint();
    }

    /**
     * Set the content pane of the window.
     * @param contentPane The content pane to set.
     */
    public void setContentPane(Div contentPane) {
        removeAll();

        this.contentPane = contentPane;
        repaint();
    }
 }
