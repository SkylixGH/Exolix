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

    /**
     * Apply this layout to the element. This application isn't
     * permanant, any position changes or anything else on the element
     * will not trigger a layout recalculation, unless specificly programmeed
     * to do so.
     * 
     * @param element The element to apply the layout to.
     */
    public void apply(Element element) {
        render(element, element.getParent());
    }
}
