package net.skylix.elixor.desktop.renderer.layout;

import net.skylix.elixor.desktop.renderer.element.Element;

/**
 * A layout object that should be used to
 * manipulate the layout of the elements.
 */
public abstract class Layout {
    /**
     * This method is called when the UI has been rendered.
     *
     * @param element The element that has been rendered.
     * @param parent The parent of the element.
     */
    public abstract void render(Element element, Element parent);
}
