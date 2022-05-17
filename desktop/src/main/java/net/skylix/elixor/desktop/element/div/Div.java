package net.skylix.elixor.desktop.element.div;

import net.skylix.elixor.desktop.element.Component;
import net.skylix.elixor.desktop.element.Element;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;
import java.awt.geom.Path2D;

public class Div extends Component {
    private int w;
    private int h;
    private int x;
    private int y;
    private Component parent;

    public Div() {
        super();

        // Random nums between 50 and 100
        w = (int) (Math.random() * 50 + 50);
        h = (int) (Math.random() * 50 + 50);

        x = w / 2;
        y = h / 2;
    }

    /**
     * Render the element.
     *
     * @param g2d The graphics output.
     * @param window The window.
     * @param parent The parent element.
     */
    @Override
    public void render(Graphics2D g2d, Window window, Component parent) {
        Path2D.Float shape = new Path2D.Float();
        this.parent = parent;

        shape.moveTo(x, y);
        shape.lineTo(x + w, y);
        shape.lineTo(x + w, y + h);
        shape.lineTo(x, y + h);
        shape.closePath();

        final int mouseX = window.getMouseX();
        final int mouseY = window.getMouseY();

        if (shape.contains(mouseX, mouseY)) {
            g2d.setColor(Color.RED);

            Component parent2 = getParent();
            System.out.println(parent2.getClass());
        } else {
            g2d.setColor(Color.BLUE);
        }

        g2d.draw(shape);
        g2d.fill(shape);
    }

    /**
     * Get the width of the div container.
     *
     * @return The width.
     */
    @Override
    public int getWidth() {
        return w;
    }

    /**
     * Get the height of the div container.
     *
     * @return The height.
     */
    @Override
    public int getHeight() {
        return h;
    }

    @Override
    public Size getMinimumSize() {
        return null;
    }

    @Override
    public Size getMaximumSize() {
        return null;
    }

    @Override
    public Component getParent() {
        return parent;
    }
}
