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
}
 