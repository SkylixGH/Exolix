package net.skylix.elixor.desktop.renderer.canvas;

import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class is an extended JavaX Swing componnet that is used to render the GUI.
 */
public class Canvas extends JComponent {
    

    /**
     * This method paints the actual renderer stream to this component.
     * 
     * @param sg The Swing graphics object.
     */
    @Override
    public void paintComponent(java.awt.Graphics sg) {
        super.paintComponent(sg);

        Graphics2D g = (Graphics2D) sg;

        g.setColor(Color.black);
        g.drawRect(0, 0, 255, 255);
    }
}
