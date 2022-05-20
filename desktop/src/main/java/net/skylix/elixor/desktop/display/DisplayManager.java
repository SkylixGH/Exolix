package net.skylix.elixor.desktop.display;

import net.skylix.elixor.desktop.window.Window;

public class DisplayManager {
    /**
     * Get the current display of a window
     * @param window The window to get the display of.
     * @return The display of the window.
     */
    public static Display getCurrentDisplay(Window window) {
        return new Display();
    }

//    public static Display[] getDisplays() {
//
//    }
}

// TODO: Implement getDisplays() and more.
