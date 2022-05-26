package net.skylix.elixor.desktop.generic.div;

import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;

import java.awt.geom.Path2D;

/**
 * A division element that can hold other element of any type.
 */
public class Div extends Element {
    /**
     * The div render method.
     * 
     * @param g The graphics painter object.
     */
    @Override
    public Path2D.Float render(Graphics g) {
        final Path2D.Float shape = g.drawRect(
            getPosition().x(),
            getPosition().y(),
            getWidth(),
            getHeight(),
            getCornerRadius(),
            getBackgroundColor(),
            0,
            null 
        );

        return shape;
    }
}
