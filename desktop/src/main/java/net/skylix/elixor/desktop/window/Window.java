package net.skylix.elixor.desktop.window;

import net.skylix.elixor.desktop.engines.HierarchyRenderer;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.events.MouseEvent;
import net.skylix.elixor.desktop.events.MouseEventType;
import net.skylix.elixor.desktop.system.microsoft.windows.WindowsJFrameProcess;
import net.skylix.elixor.desktop.system.microsoft.windowsUtil.MicrosoftWindowsController;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.unit.UnitAdapter;
import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.component.WindowDragState;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.Native;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
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
    private final JFrame jFrame;

    /**
     * All the adapters.
     */
    private final List<WindowAdapter> adapters = new ArrayList<>();

    /**
     * The window size.
     */
    private final Size size;

    /**
     * The mouse data.
     */
    private MouseEvent mouseMeta;

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
     * The Windows OS util controller.
     */
    private MicrosoftWindowsController osWindowsController;

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
        mouseMeta = new MouseEvent(0, 0, 0, false, MouseEventType.ANY);

        final Window self = this;

        clientArea = new JPanel() {
            @Override
            public void paintComponent(Graphics g3d) {
                Graphics2D g2d = (Graphics2D) g3d;
                HierarchyRenderer.render(g2d, hierarchyTree, self);

                g2d.dispose();
            }
        };

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

                fireWindowResize();
            }
        });

        clientArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        jFrame.setContentPane(clientArea);
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/defaultIcon.png"))).getImage());
        jFrame.setBackground(new Color(39, 39, 39));

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

        winJFP = new WindowsJFrameProcess(jFrame, this);
        winJFP.initializeProcess(getWindowsHandle());

        osWindowsController = new MicrosoftWindowsController(winJFP);
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
        return new Position(mouseMeta.x(), mouseMeta.y());
    }

    /**
     * Get mouse X position.
     *
     * @return Mouse X position.
     */
    public int getMouseX() {
        return getMousePosition().getXPosition();
    }

    /**
     * Get mouse Y position.
     *
     * @return Mouse Y position.
     */
    public int getMouseY() {
        return getMousePosition().getYPosition();
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
        if (winJFP != null)
            winJFP.clearAllDragInfo();
    
        clientArea.repaint();
        recursivelyProcessLayoutsNative(hierarchyTree);
    }

    /**
     * Recursively re-process all the layouts' information, and handle native properties.
     *
     * @param tree The hierarchy tree.
     */
    public void recursivelyProcessLayoutsNative(HierarchyTree tree) {
        for (Component child : tree.getElements()) {
            final Layout layout = child.getLayoutEngine();
            final Component owner = child.getTree().getOwner();

            if (winJFP != null)
                if (owner.getWindowDragState() == WindowDragState.ALLOW) {
                    winJFP.addTitleBarDragRegion(new Point[] {
                        new Point(owner.getXPosition(), owner.getYPosition()),
                        new Point(owner.getXPosition() + owner.getWidth(), owner.getYPosition() + owner.getHeight()),
                    });
                } else if (owner.getWindowDragState() == WindowDragState.DENY) {
                    winJFP.addTitleBarExcludedDragRegion(new Point[] {
                        new Point(owner.getXPosition(), owner.getYPosition()),
                        new Point(owner.getXPosition() + owner.getWidth(), owner.getYPosition() + owner.getHeight()),
                    });
                }

            if (layout != null) {
                layout.process(child.getTree(), owner);
            }

            if (child.getTree().getElements().length > 0)
                recursivelyProcessLayoutsNative(child.getTree());
        }
    }

    /**
     * Get the OS windows controller to handle specific operations related to the Windows OS.
     *
     * @return The OS windows controller.
     */
    public MicrosoftWindowsController getOSWindowsController() {
        return osWindowsController;
    }

    /**
     * Set virtual mouse location.
     *
     * @param event The mouse metadata in the form of an event.
     */
    public void setVirtualMouseMeta(MouseEvent event) {
        mouseMeta = event;

        refreshWindowProperties();
        fireMouseInteract(event);
    }

    /**
     * Add a new listener to the window.
     *
     * @param listener The listener to add.
     */
    public void addListener(WindowAdapter listener) {
        adapters.add(listener);
    }

    /**
     * Remove a listener from the window.
     *
     * @param listener The listener to remove.
     */
    public void removeListener(WindowAdapter listener) {
        adapters.remove(listener);
    }

    /**
     * Fire all mouse interaction events.
     *
     * @param event The mouse event.
     */
    private void fireMouseInteract(MouseEvent event) {
        for (WindowAdapter adapter : adapters) {
            adapter.onMouseInteract(event);
        }
    }

    /**
     * Fire the window resize event.
     */
    private void fireWindowResize() {
        for (WindowAdapter adapter : adapters) {
            adapter.onResize();
        }
    }

    /**
     * Stop the window, and close it.
     */
    public void stop() {
        if (jFrame.isVisible()) {
            jFrame.setVisible(false);
            jFrame.dispose();
        }
    }

    /**
     * Is mouse pressed.
     *
     * @return True if the mouse is pressed.
     */
    public boolean isMousePressed() {
        return mouseMeta.pressed();
    }

    /**
     * Get the current mouse button.
     *
     * @return The current mouse button.
     */
    public int getMouseButton() {
        return mouseMeta.button();
    }

    /**
     * Set the position of the window.
     *
     * @param position The new position.
     */
    public void setPosition(Position position) {
        jFrame.setLocation(position.getXPosition(), position.getYPosition());
        refreshWindowProperties();
    }

    /**
     * Set the X position of the window.
     *
     * @param x The new X position.
     */
    public void setXPosition(int x) {
        jFrame.setLocation(x, jFrame.getY());
        refreshWindowProperties();
    }

    /**
     * Set the Y position of the window.
     *
     * @param y The new Y position.
     */
    public void setYPosition(int y) {
        jFrame.setLocation(jFrame.getX(), y);
        refreshWindowProperties();
    }

    /**
     * Move the window to the center.
     */
    public void moveToCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = jFrame.getSize();

        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;

        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;

        setPosition(new Position((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2));
    }

    /**
     * Allow window resizing.
     */
    public void enableResizeSupport() {
        jFrame.setResizable(true);
    }

    /**
     * Deny window resizing.
     */
    public void disableResizeSupport() {
        jFrame.setResizable(false);
    }

    /**
     * Is window resizing allowed.
     *
     * @return True if window resizing is allowed.
     */
    public boolean isResizeAllowed() {
        return jFrame.isResizable();
    }

    /**
     * Enable always on top. When enabled, the window will always be on top of other windows.
     */
    public void enableAlwaysOnTop() {
        jFrame.setAlwaysOnTop(true);
    }

    /**
     * Disable always on top.
     */
    public void disableAlwaysOnTop() {
        jFrame.setAlwaysOnTop(false);
    }

    /**
     * Is always on top enabled.
     *
     * @return True if always on top is enabled.
     */
    public boolean isAlwaysOnTopEnabled() {
        return jFrame.isAlwaysOnTop();
    }
}
