package net.skylix.elixor.desktop.window;

import net.skylix.elixor.desktop.element.Element;
import net.skylix.elixor.desktop.element.div.Div;
import net.skylix.elixor.desktop.engines.HierarchyRenderer;
import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.unit.UnitAdapter;

import javax.swing.*;
import java.awt.*;
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
     * The hierarchy tree of the window.
     */
    private final HierarchyTree hierarchyTree;

    /**
     * The base client area.
     */
    private final JComponent clientArea;

    /**
     * Create a new window.
     *
     * @param title The title of the window.
     */
    public Window(String title) {
        this.title = title;

        size = new Size();
        jFrame = new JFrame(title);
        hierarchyTree = new HierarchyTree();

        clientArea = new JPanel() {
            @Override
            public void paintComponent(Graphics g3d) {
                Graphics2D g2d = (Graphics2D) g3d;
                HierarchyRenderer.render(g2d, hierarchyTree);

                g2d.dispose();
            }
        };

        size.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                refreshWindowProperties();
            }
        });

        jFrame.setContentPane(clientArea);
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/defaultIcon.png"))).getImage());

        Div root = new Div();
        Div panel = new Div();

        root.add(panel);

        hierarchyTree.add(root);
        refreshWindowProperties();
    }

    /**
     * Calculate and set all the window properties.
     */
    private void refreshWindowProperties() {
        jFrame.setSize(size.getWidth(), size.getHeight());
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    public void add(Element element) {
        hierarchyTree.add(element);
    }
}
