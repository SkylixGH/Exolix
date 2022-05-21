package net.skylix.elixor.desktop.component.div;

import net.skylix.elixor.desktop.component.Component;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.engines.layout.BorderLayout;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.unit.UnitAdapter;
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
    private Color backgroundColor;

    /**
     * The border color of the element.
     */
    private Color borderColor;

    /**
     * The text color of the element.
     */
    private Color textColor;

    /**
     * The parent window.
     */
    private Window window;

    /**
     * The parent element.
     */
    private Component parent;

    /**
     * Clip edges.
     */
    private boolean clip = true;

    /**
     * The shape.
     */
    private Path2D.Float shape;

    /**
     * Border radius.
     */
    private final BorderRadius radius;

    /**
     * The element render position.
     */
    private final Position position;

    /**
     * The layout processor.
     */
    private Layout layoutEngine;

    /**
     * Create a new division element.
     */
    public Div() {
        size = new Size(0, 0);
        radius = new BorderRadius();
        position = new Position();
        layoutEngine = new BorderLayout();

        backgroundColor = new Color(0, 0, 0, 0);
        borderColor = new Color(0, 0, 0);
        textColor = new Color(0, 0, 0);
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

        System.out.println("Div render");

        shape = new Path2D.Float();

        size.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                refresh();
            }
        });

        position.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                refresh();
            }
        });

        radius.addListener(new UnitAdapter() {
            @Override
            public void onChange() {
                refresh();
            }
        });

        final int topLeftRadius = (int) radius.getTopLeft();
        final int topRightRadius = (int) radius.getTopRight();
        final int bottomLeftRadius = (int) radius.getBottomLeft();
        final int bottomRightRadius = (int) radius.getBottomRight();

        final int width = size.getWidth();
        final int height = size.getHeight();

        final int x = position.getXPosition();
        final int y = position.getYPosition();

        shape.moveTo(x + topLeftRadius, y);
        shape.lineTo(x + width - topRightRadius, y);
        shape.quadTo(x + width, y, x + width, y + topRightRadius);
        shape.lineTo(x + width, y + height - bottomRightRadius);
        shape.quadTo(x + width, y + height, x + width - bottomRightRadius, y + height);
        shape.lineTo(x + bottomLeftRadius, y + height);
        shape.quadTo(x, y + height, x, y + height - bottomLeftRadius);
        shape.lineTo(x, y + topLeftRadius);
        shape.quadTo(x, y, x + topLeftRadius, y);
        shape.closePath();

        if (parent instanceof final Div parentDiv) {
            if (parentDiv.isEdgeClipped()) {
                g2d.setClip(parentDiv.getShape());
            }
        }

        g2d.setColor(backgroundColor);
        g2d.fill(shape);
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

    /**
     * Get the size.
     * 
     * @return The size.
     */
    @Override
    public Size getSize() {
        return size;
    }

    /**
     * Get the minimum size.
     */
    @Override
    public Size getMinimumSize() {
        return new Size(0, 0);
    }

    /**
     * Get the maximum size.
     */
    @Override
    public Size getMaximumSize() {
        return size;
    }

    /**
     * Set the background color.
     * 
     * @param backgroundColor The background color.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        refresh();
    }

    /**
     * Set the border color.
     * 
     * @param borderColor The border color.
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        refresh();
    }

    /**
     * Set the text color.
     * 
     * @param textColor The text color.
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        refresh();
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
     * Get the border color.
     * 
     * @return The border color.
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Get the text color.
     * 
     * @return The text color.
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Get the shape for the element.
     * 
     * @return The shape.
     */
    @Override
    public Shape getShape() {
        return shape != null ? shape : new Path2D.Float();
    }

    /**
     * Get mouse position.
     *
     * @return The mouse position.
     */
    @Override
    public Position getMousePosition() {
        return new Position(
                window.getMousePosition().getXPosition() - position.getXPosition(),
                window.getMousePosition().getYPosition() - position.getYPosition()
        );
    }

    /**
     * Is edge clipping enabled.
     *
     * @return True if enabled.
     */
    public boolean isEdgeClipped() {
        return clip;
    }

    /**
     * Enable edge clipping.
     */
    public void enableEdgeClipping() {
        clip = true;
        refresh();
    }

    /**
     * Disable edge clipping.
     */
    public void disableEdgeClipping() {
        clip = false;
        refresh();
    }

    /**
     * Set the position.
     * 
     * @param position The position.
     */
    @Override
    public void setPosition(Position position) {
        this.position.setX(position.getXPosition());
        this.position.setY(position.getYPosition());
    }

    /**
     * Get the position.
     * 
     * @return The position.
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Get the border radius.
     * 
     * @return The border radius.
     */
    public BorderRadius getBorderRadius() {
        return radius;
    }

    /**
     * Get the top left border radius.
     * 
     * @return The top left border radius.
     */
    public int getTopLeftRadius() {
        return (int) radius.getTopLeft();
    }

    /**
     * Get the top right border radius.
     * 
     * @return The top right border radius.
     */
    public int getTopRightRadius() {
        return (int) radius.getTopRight();
    }

    /**
     * Get the bottom left border radius.
     * 
     * @return The bottom left border radius.
     */
    public int getBottomLeftRadius() {
        return (int) radius.getBottomLeft();
    }

    /**
     * Get the bottom right border radius.
     * 
     * @return The bottom right border radius.
     */
    public int getBottomRightRadius() {
        return (int) radius.getBottomRight();
    }

    /**
     * Set the border radius.
     * 
     * @param radius The border radius.
     */
    public void setBorderRadius(BorderRadius radius) {
        this.radius.setTopLeft(radius.getTopLeft());
        this.radius.setTopRight(radius.getTopRight());
        this.radius.setBottomLeft(radius.getBottomLeft());
        this.radius.setBottomRight(radius.getBottomRight());
    }

    /**
     * Get the layout engine.
     * 
     * @return The layout engine.
     */
    @Override
    public Layout getLayoutEngine() {
        return layoutEngine;
    }

    /**
     * Set the layout engine.
     * 
     * @param layoutEngine The layout engine.
     */
    @Override
    public void setLayoutEngine(Layout layoutEngine) {
        this.layoutEngine = layoutEngine;
        refresh();
    }

    /**
     * Check to see if the mouse is over the element.
     * 
     * @return True if the mouse is over the element.
     */
    @Override
    public boolean isMouseOver() {
        try {
            return getShape().contains(
                getMouseX() + getPosition().getXPosition(),
                getMouseY() + getPosition().getYPosition()
            );
        } catch (Exception error) {
            return false;
        }
    }
}
