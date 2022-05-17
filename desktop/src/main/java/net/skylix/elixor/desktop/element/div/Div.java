package net.skylix.elixor.desktop.element.div;

import net.skylix.elixor.desktop.element.Component;
import net.skylix.elixor.desktop.element.Element;

import java.awt.*;

public class Div extends Component {
    /**
     * Render the element.
     *
     * @param g2d The graphics output.
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.drawRect(getWidth() / 2, getHeight() / 2, getWidth(), getHeight());

        System.out.println(getClass().getName());
    }

    /**
     * Get the width of the div container.
     *
     * @return The width.
     */
    @Override
    public int getWidth() {
        return (int) (Math.random() * (50 - 100) + 100); // TODO: Implement this method
    }

    /**
     * Get the height of the div container.
     *
     * @return The height.
     */
    @Override
    public int getHeight() {
        return (int) (Math.random() * (50 - 100) + 100); // TODO: Implement this method
    }
}
