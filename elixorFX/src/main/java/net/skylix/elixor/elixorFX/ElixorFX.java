package net.skylix.elixor.elixorFX;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import net.skylix.elixor.elixorFX.windows.customJFrame.CustomJFrame;
import net.skylix.elixor.elixorFX.windows.theme.DarkTheme;

import javax.swing.*;

import static com.sun.jna.Native.*;
import static com.sun.jna.Platform.isWindows;

public class ElixorFX {
    /**
     * Extended client area example
     */
    public static void init() {
        CustomJFrame frame = new CustomJFrame(new DarkTheme(), "ElixorFX");

        frame.setVisible(true);
    }

    public static class ModJFrame extends JFrame {
        public ModJFrame() {
            super();
        }

        public WinDef.HWND getHWND() {
            WinDef.HWND hwnd = new WinDef.HWND();
            hwnd.setPointer(getComponentPointer(this));

            return hwnd;
        }
    }
}
