package net.skylix.elixor.desktop.local;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import net.skylix.elixor.desktop.local.windows.WindowsJFrameProcess;

import javax.swing.*;

public class ModJFrame extends JFrame {
    private WindowsJFrameProcess winProcess;

    public ModJFrame(String title, boolean customHit, int titleBarHeightHitTest) {
        super(title);

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            winProcess = new WindowsJFrameProcess(customHit, titleBarHeightHitTest);
        }
    }

    @Override
    public final void setVisible(boolean b) {
        if (this.isVisible() && b) {
            super.setVisible(false);
        }

        super.setVisible(b);

        if (b && winProcess != null) {
            winProcess.initializeProcess(getHWND());
        }
    }

    private WinDef.HWND getHWND() {
        final WinDef.HWND handle = new WinDef.HWND();
        handle.setPointer(Native.getComponentPointer(this));

        return handle;
    }
}
