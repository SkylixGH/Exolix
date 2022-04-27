package net.skylix.elixor.desktop.local;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import net.skylix.elixor.desktop.local.windows.JFrameProcess;

import javax.swing.*;

public class ModJFrame extends JFrame {
    private JFrameProcess winProcess;

    public ModJFrame(String title) {
        super(title);

        // only if os is windows
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            winProcess = new JFrameProcess(false, 0);
        }
    }

    public ModJFrame() {
        super();
        new ModJFrame("Elixor Desktop [net.skylix.elixor.desktop]");
    }

    @Override
    public void setVisible(boolean b) {
        if (this.isVisible() && b) {
            super.setVisible(false);
        }

        super.setVisible(b);

        if (b && winProcess != null) {
            winProcess.initializeProcess(getHWND());
        }
    }

    private WinDef.HWND getHWND() {
        WinDef.HWND handle = new WinDef.HWND();
        handle.setPointer(Native.getComponentPointer(this));

        return handle;
    }
}
