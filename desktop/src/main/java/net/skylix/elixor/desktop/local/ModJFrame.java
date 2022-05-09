package net.skylix.elixor.desktop.local;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import net.skylix.elixor.desktop.local.windows.WindowsJFrameProcess;

import javax.swing.*;

public class ModJFrame extends JFrame {
    private WindowsJFrameProcess winProcess;
    private Runnable onMaximize;
    private Runnable onUnMaximize;
    private Runnable onClosing;
    private boolean didTriggerMaximize = false;
    private boolean didTriggerUnMaximize = false;

    public ModJFrame(String title, boolean customHit, int titleBarHeightHitTest) {
        super(title);

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            winProcess = new WindowsJFrameProcess(customHit, titleBarHeightHitTest, this);
        }
    }

    @Override
    public final void setVisible(boolean b) {
        if (this.isVisible() && b) {
            return;
        }

        if (b) {
            super.setVisible(true);

            if (winProcess != null) {
                winProcess.initializeProcess(getHWND());
            }

            return;
        }

        winProcess.closeWindowNative();
        super.setVisible(false);
    }

    private WinDef.HWND getHWND() {
        final WinDef.HWND handle = new WinDef.HWND();
        handle.setPointer(Native.getComponentPointer(this));

        return handle;
    }

    public final void maximizeNative() {
        if (winProcess != null) {
            winProcess.maximize();
        }
    }

    public final void minimizeNative() {
        if (winProcess != null) {
            winProcess.minimize();
        }
    }

    public final void triggerMaximized() {
        if (onMaximize != null) {
            didTriggerMaximize = true;
            didTriggerUnMaximize = false;

            onMaximize.run();
        }
    }

    public final void setOnMaximizeRunnable(Runnable onMaximize) {
        this.onMaximize = onMaximize;
    }

    public final void triggerUnMaximized() {
        if (onUnMaximize != null) {
            didTriggerUnMaximize = true;
            didTriggerMaximize = false;

            onUnMaximize.run();
        }
    }

    public final void setOnUnMaximizeRunnable(Runnable onUnMaximize) {
        this.onUnMaximize = onUnMaximize;
    }

    public final void triggerClosing() {
        if (onClosing != null) {
            onClosing.run();
        }
    }

    public final void setOnClosingRunnable(Runnable onClosing) {
        this.onClosing = onClosing;
    }

    public final WindowsJFrameProcess getWinProcess() {
        return winProcess;
    }

    public final boolean hasTriggeredMaximized() {
        return didTriggerMaximize;
    }

    public final boolean hasTriggeredUnMaximized() {
        return didTriggerUnMaximize;
    }
}
