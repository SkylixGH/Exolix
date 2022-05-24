package net.skylix.elixor.desktop.renderer.element;

import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Size;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A very basic structure for an element, this enforces some basic universal methods such as getters and setters for the
 * scale, color, and other related properties.
 */
public abstract class Element {
    /**
     * All of th element children that nest
     * inside of this element, to later be rendered
     * via recursion.
     */
    private final List<Element> nodes = new ArrayList<>();

    /**
     * The identifier for this element.
     */
    private String id = "";

    /**
     * The parent of this element, this value will
     * be null if this element is either in the root 
     * or if the UI/X has not rendered.
     */
    private Element parent;

    /**
     * The render method for the element. This method will draw all base shape of the element,
     * children of this element will be rendered, by, and only by the core rendering engine based on this element's
     * width, height, borders, margins, paddings, and other related properties.
     * 
     * @param g The graphics object to render to.
     * @return The shape of the rendered element.
     */
    public abstract Path2D.Float render(Graphics graphics);

    /**
     * Get the size of the element. This size is in the form of a record
     * containing the width and height.
     * 
     * @return The size of the element.
     */
    public abstract Size getSize();

    /**
     * Get the prefered layout size, this is the size that
     * the element would like to be, this size is generally determined
     * by the element parent's layout manager.
     * 
     * @return The prefered layout size.
     */
    public abstract Size getAutoSize();

    /**
     * Get the minimum possible size that is allowed for the element
     * during the rendering stage.
     * 
     * @return The minimum possible size.
     */
    public abstract Size getMinSize();

    /**
     * Get the maximum possible size that is allowed for the element
     * during the rendering stage.
     * 
     * @return The maximum possible size.
     */
    public abstract Size getMaxSize();

    /**
     * Set the size of the element.
     * 
     * @param size The size of the element.
     */
    public abstract void setSize(Size size);

    /**
     * Set the minimum size of the element.
     * 
     * @param size The minimum size of the element.
     */
    public abstract void setMinSize(Size size);

    /**
     * Set the maximum size of the element.
     * 
     * @param size The maximum size of the element.
     */
    public abstract void setMaxSize(Size size);

    /**
     * Get the width of this element.
     * 
     * @return The width of this element.
     */
    public int getWidth() {
        return getSize().width();
    }

    /**
     * Get the height of this element.
     * 
     * @return The height of this element.
     */
    public int getHeight() {
        return getSize().height();
    }

    /**
     * Get all of the nested nodes
     * from this element.
     * 
     * @return All of the nested nodes.
     */
    public Element[] getNodes() {
        return nodes.toArray(new Element[0]);
    }

    /**
     * Get the identifier for this element,
     * the identifier doesn't always have to be unique.
     * 
     * @return The identifier for this element.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the identifier for this element,
     * the identifier doesn't always have to be unique.
     * 
     * @param id The identifier for this element.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Add an element to this element's list of nodes.
     * 
     * @param element The element to add.
     */
    public void add(Element element) {
        nodes.add(element);
    }

    /**
     * Set the parent element, use null if the element
     * has no parent.
     * 
     * @param parent The parent element.
     */
    public void setParent(Element parent) {
        this.parent = parent;
    }

    /**
     * Get the parent element, this value will
     * be null if this element is either in the root 
     * or if the UI/X has not rendered.
     * 
     * @return The parent element.
     */
    public Element getParent() {
        return parent;
    }

    /**
     * Set the background color.
     * 
     * @param color The background color.
     */
    public abstract void setBackgroundColor(Color color);

    /**
     * Get the background color.
     * 
     * @return The background color.
     */
    public abstract Color getBackgroundColor();

    /**
     * Get the border/corner radius.
     * 
     * @return The border radius.
     */
    public abstract CornerRadius getCornerRadius();

    /**
     * Set the border/corner radius.
     * 
     * @param cornerRadius The border radius.
     */
    public abstract void setCornerRadius(CornerRadius cornerRadius);
}
