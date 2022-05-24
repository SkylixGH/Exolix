package net.skylix.elixor.desktop.renderer.canvas;

import javax.swing.JComponent;

import net.skylix.elixor.desktop.renderer.color.Color;

/**
 * This class is an extended JavaX Swing componnet that is used to render the GUI.
 */
public class Canvas extends JComponent {
    /**
     * This method paints the actual renderer stream to this component.
     * We recommend that you extend this class and override this method.
     * Once you do that, it will expose the Java AWT graphics object which 
     * can be used to initialize the custom graphics processor.
     * 
     * @param sg The Swing graphics object.
     */
    @Override
    public void paintComponent(java.awt.Graphics sg) {
        super.paintComponent(sg);
    }
}
