package net.skylix.elixor.desktop.renderer.element;

import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.renderer.layout.Layout;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Location;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

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
     * inside this element, to later be rendered
     * via recursion.
     */
    private final List<Element> nodes = new ArrayList<>();

    /**
     * The identifier for this element.
     */
    private String id = "";

    /**
     * The background color.
     */
    private Color backgroundColor = Color.TRANSPARENT;

    /**
     * The parent of this element, this value will
     * be null if this element is either in the root 
     * or if the UI/X has not rendered.
     */
    private Element parent;

    /**
     * Whether to skip dispatcher refresh jobs.
     */
    private boolean skipDispatcher = false;

    /**
     * The current absolute position of this element
     * that is relative to the base viewport.
     */
    private Location position = new Location(0, 0);

    /**
     * The current element shape.
     */
    private Path2D.Float shape;

    /**
     * The corner radius.
     */
    private CornerRadius cornerRadius = new CornerRadius(0, 0, 0, 0);

    /**
     * The parent window.
     */
    private Window parentWindow;

    /**
     * The layout engine.
     */
    private Layout layout;

    /**
     * The size of this element.
     */
    private Size size = null;

    /**
     * The automatic size from the layout.
     */
    private Size autoSize = new Size(0, 0);

    /**
     * The minimum size of this element.
     */
    private Size minSize = new Size(0, 0);

    /**
     * The maximum size of this element.
     */
    private Size maxSize = new Size(0, 0);

    /**
     * The render method for the element. This method will draw all base shape of the element,
     * children of this element will be rendered, by, and only by the core rendering engine based on this element's
     * width, height, borders, margins, paddings, and other related properties.
     *
     * @param graphics The graphics object to render to.
     * @return The shape of the rendered element.
     */
    public abstract Path2D.Float render(final Graphics graphics);

    /**
     * Get the size of the element. This size is in the form of a record
     * containing the width and height.
     *
     * @return The size of the element.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Get the preferred layout size, this is the size that
     * the element would like to be, this size is generally determined
     * by the element parent's layout manager.
     *
     * @return The preferred layout size.
     */
    public Size getAutoSize() {
        return autoSize;
    }

    /**
     * Set the viewport layout prcessing engine.
     * 
     * @param engine The engine to set.
     */
    public void setLayoutEngine(Layout engine) {
        layout = engine;
    }

    /**
     * Get the current layout engine.
     * 
     * @return The current layout engine.
     */
    public Layout getLayoutEngine() {
        return layout;
    }

    /**
     * Get the minimum possible size that is allowed for the element
     * during the rendering stage.
     *
     * @return The minimum possible size.
     */
    public Size getMinSize() {
        return minSize;
    }

    /**
     * Get the maximum possible size that is allowed for the element
     * during the rendering stage.
     *
     * @return The maximum possible size.
     */
    public Size getMaxSize() {
        return maxSize;
    }

    /**
     * Set the size of the element.
     *
     * @param size The size of the element.
     */
    public void setSize(final Size size) {
        this.size = size;
    }

    /**
     * Set the automatic size of the element.
     *
     * @param autoSize The automatic size of the element.
     */
    public void setAutoSize(final Size autoSize) {
        this.autoSize = autoSize;
    }

    /**
     * Set the minimum size of the element.
     *
     * @param size The minimum size of the element.
     */
    public void setMinSize(final Size size) {
        this.minSize = size;
    }

    /**
     * Set the maximum size of the element.
     *
     * @param size The maximum size of the element.
     */
    public void setMaxSize(final Size size) {
        this.maxSize = size;
    }

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
     * Get all the nested nodes
     * from this element.
     * 
     * @return All the nested nodes.
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
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Add an element to this element's list of nodes.
     * 
     * @param element The element to add.
     */
    public void add(final Element element) {
        nodes.add(element);
    }

    /**
     * Set the parent element, use null if the element
     * has no parent.
     * 
     * @param parent The parent element.
     */
    public void setParent(final Element parent) {
        this.parent = parent;
    }

    /**
     * Process the layout engine. This will trigger 
     * the current layout engine if it is configured
     * and working correctly.
     */
    public void processLayout() {
        if (layout != null) {
            layout.apply(this);
        }
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
    public void setBackgroundColor(final Color color) {
        backgroundColor = color;
    }

    /**
     * Get the background color.
     * 
     * @return The background color.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Get the border/corner radius.
     * 
     * @return The border radius.
     */
    public CornerRadius getCornerRadius() {
        return cornerRadius;
    }

    /**
     * Set the border/corner radius.
     * 
     * @param cornerRadius The border radius.
     */
    public void setCornerRadius(final CornerRadius cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * Get the shape of the element. This shape is great for collision detection
     * and other things.
     *
     * @return The shape of the element.
     */
    public Path2D.Float getShape() {
        return shape;
    }

    /**
     * Check to see if the application's virtual cursor is
     * directly above the element, it doesn't matter if this
     * element is being covered by another element, as long
     * as the mouse is directly above this element and or
     * inside this element.
     *
     * @return True if the cursor is inside the element, false if it is not.
     */
    public boolean isVCursorOverDirect() {
        final Path2D.Float shape = getShape();

        if (shape == null) {
            return false;
        }

        return true; /// TODO: Implement
    }

    /**
     * Set the parent window.
     *
     * @param window The parent window.
     */
    public void setParentWindow(final Window window) {
        parentWindow = window;
    }

    /**
     * Get the parent window.
     *
     * @return The parent window.
     */
    public Window getParentWindow() {
        return parentWindow;
    }

    /**
     * Set the absolute position of the element.
     * This position is directly relative to
     * the viewport.
     *
     * @param position The absolute position.
     */
    public void setPosition(final Location position) {
        this.position = position;
    }

    /**
     * Get the absolute position of the element.
     *
     * @return The absolute position.
     */
    public Location getPosition() {
        return position;
    }
}
