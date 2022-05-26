package net.skylix.elixor.desktop.renderer.gpu;

import java.awt.GraphicsEnvironment;

/**
 * An API system to getting information from the GPU, and or to manipulate the GPU.
 */
public class GPU {
    /**
     * Get the maximum framerate that the GPU can push to the screen.
     * 
     * @return The maximum framerate that the GPU can push to the screen.
     */
    public static int getFrameRate() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
    }

    /**
     * Set hardware acceleration state. Enabling this means that all supported GPU features will be used.
     * Although this package is included in the desktop module for the Elixor framework, it can be used in other
     * applications.
     *
     * @param state The state to set hardware acceleration to.
     */
    public static void setHardwareAcceleration(boolean state) {
        if (state) {
            System.setProperty("sun.java2d.opengl", "true");
        } else {
            System.setProperty("sun.java2d.opengl", "false");
        }
    }
}
 