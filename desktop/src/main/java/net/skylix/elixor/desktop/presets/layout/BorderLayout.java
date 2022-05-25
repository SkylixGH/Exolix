package net.skylix.elixor.desktop.presets.layout;

import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.layout.Layout;
import net.skylix.elixor.desktop.unit.Location;

import javax.swing.text.Position;

/**
 * A layout object that should be used to
 * lay elements in a border flow.
 */
public class BorderLayout extends Layout {
    /**
     * Adjust the element positions on render.
     *
     * @param element The element that has been rendered.
     * @param parent The parent of the element.
     */
    @Override
    public void render(Element element, Element parent) {
        int lx = 0;

        for (Element e : element.getNodes()) {
            e.setPosition(new Location(lx, 0));
            lx += 10 + e.getWidth();
        }
    }
}
