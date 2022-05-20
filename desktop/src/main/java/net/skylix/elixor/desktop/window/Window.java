package net.skylix.elixor.desktop.window;

import net.skylix.elixor.desktop.engines.HierarchyRenderer;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.system.microsoft.windows.WindowsJFrameProcess;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.unit.UnitAdapter;
import net.skylix.elixor.desktop.component.Component;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.Native;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * A window class for creating windows.
 */
public class Window {
    /**
     * The title of the window.
     */
    private String title;

    /**
     * Swing window.
     */
    private JFrame jFrame;

    /**
     * The window size.
     */
    private final Size size;

    /**
     * The mouse position.
     */
    private final Position mousePosition;

    /**
     * The hierarchy tree of the window.
     */
    private final HierarchyTree hierarchyTree;

    /**
     * The base client area.
     */
    private final JComponent clientArea;

    /**
     * The window JFrame process for the Windows OS.
     */
    private WindowsJFrameProcess winJFP;

    /**
     * Create a new window.
     *
     * @param title The title of the window.
     */
    public Window(String title) {
        this.title = title;

        size = new Size();
        jFrame = new JFrame(title);
        hierarchyTree = new HierarchyTree(null);
        mousePosition = new Position();

        final Window self = this;

        clientArea = new JPanel() {
            @Override
            public void paintComponent(Graphics g3d) {

                Graphics2D g2d = (Graphics2D) g3d;
                HierarchyRenderer.render(g2d, hierarchyTree, self);

                g2d.dispose();
            }
        };

        clientArea.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition.setX(e.getX());
                mousePosition.setY(e.getY());

                refreshWindowProperties();
            }
        });

        size.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                jFrame.setSize(size.getWidth(), size.getHeight());
                refreshWindowProperties();
            }
        });

        jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                size.pauseOnChange();

                size.setWidth(e.getComponent().getWidth());
                size.setHeight(e.getComponent().getHeight());

                size.resumeOnChange();
                refresh();
            }
        });

        jFrame.setContentPane(clientArea);
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/defaultIcon.png"))).getImage());
        jFrame.setBackground(new Color(20, 20, 20));
        
        clientArea.setOpaque(false);

        refreshWindowProperties();
    }

    /**
     * Calculate and set all the window properties.
     */
    private void refreshWindowProperties() {
        clientArea.repaint();
    }

    /**
     * Get the window title.
     *
     * @return The window title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the window title.
     *
     * @param title The new window title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Run the window to make it visible.
     */
    public void run() {
        if (jFrame.isVisible()) return;
        jFrame.setVisible(true);

        winJFP = new WindowsJFrameProcess(jFrame);
        winJFP.initializeProcess(getWindowsHandle());

        // winJFP.addTitleBarDragRegion(new Point[] {
        //     new Point(0, 0),
        //     new Point(size.getWidth(), 32),
        // });
    }

    /**
     * Get the window handle.
     * 
     * @return The window handle.
     */
    private WinDef.HWND getWindowsHandle() {
        WinDef.HWND handle = new WinDef.HWND();
        handle.setPointer(Native.getComponentPointer(jFrame));

        return handle;
    }

    /**
     * Set the window dimensions.
     *
     * @param size The new window size.
     */
    public void setSize(Size size) {
        this.size.setWidth(size.getWidth());
        this.size.setHeight(size.getHeight());

        refreshWindowProperties();
    }

    /**
     * Get the window size.
     *
     * @return The window size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Get the hierarchy tree of the window.
     *
     * @return The hierarchy tree of the window.
     */
    public HierarchyTree getHierarchyTree() {
        return hierarchyTree;
    }

    /**
     * Add an element to the hierarchy tree.
     *
     * @param element The element to add.
     */
    public void add(Component element) {
        hierarchyTree.add(element);
        refreshWindowProperties();
    }

    /**
     * Get mouse position.
     *
     * @return Mouse position.
     */
    public Position getMousePosition() {
        return mousePosition;
    }

    /**
     * Get mouse X position.
     *
     * @return Mouse X position.
     */
    public int getMouseX() {
        return mousePosition.getX();
    }

    /**
     * Get mouse Y position.
     *
     * @return Mouse Y position.
     */
    public int getMouseY() {
        return mousePosition.getY();
    }

    /**
     * Get the window width.
     *
     * @return The window width.
     */
    public int getWidth() {
        return size.getWidth();
    }

    /**
     * Get the window height.
     *
     * @return The window height.
     */
    public int getHeight() {
        return size.getHeight();
    }

    /**
     * Refresh the renderer, this will cause the content area to be repainted.
     */
    public void refresh() {
        clientArea.repaint();
        recursivelyProcessLayouts(hierarchyTree);
    }

    /**
     * Recursively re-process all the layouts' information.
     * 
     * @param tree The hierarchy tree.
     */
    public void recursivelyProcessLayouts(HierarchyTree tree) {
        for (Component child : tree.getElements()) {
            final Layout layout = child.getLayoutEngine();

            if (layout != null) {
                layout.process(child.getTree(), child.getTree().getOwner());
            }

            if (child.getTree().getElements().length > 0) 
                recursivelyProcessLayouts(child.getTree());
        }    
    }
}
