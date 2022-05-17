package net.skylix.elixor.desktop.element.div;

import net.skylix.elixor.desktop.element.Component;
import net.skylix.elixor.desktop.element.Element;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * A simple division element, this is a generic element.
 */
public class Div extends Component {
    /**
     * The size of the element.
     */
    private final Size size;

    /**
     * The background color of the element.
     */
    private final Color backgroundColor;

    /**
     * The border color of the element.
     */
    private final Color borderColor;

    /**
     * The text color of the element.
     */
    private final Color textColor;

    /**
     * The parent window.
     */
    private Window window;

    /**
     * The parent element.
     */
    private Component parent;

    /**
     * Create a new division element.
     */
    public Div() {
        size = new Size();
        backgroundColor = Color.WHITE;
        borderColor = Color.BLACK;
        textColor = Color.BLACK;
    }

    /**
     * The render method to calculate and paint the element.
     *
     * @param g2d The graphics output.
     * @param window The window.
     * @param parent The parent element.
     */
    @Override
    public void render(Graphics2D g2d, Window window, Component parent) {
        this.parent = parent;
        this.window = window;
    }

    /**
     * Get the parent container.
     *
     * @return The parent container.
     */
    @Override
    public Component getParent() {
        return parent;
    }

    /**
     * Get the parent window.
     *
     * @return The parent window.
     */
    @Override
    public Window getWindow() {
        return window;
    }
}
