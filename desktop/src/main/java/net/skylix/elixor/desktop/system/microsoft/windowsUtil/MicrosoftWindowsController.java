package net.skylix.elixor.desktop.system.microsoft.windowsUtil;

import net.skylix.elixor.desktop.system.microsoft.windows.Constants;
import net.skylix.elixor.desktop.system.microsoft.windows.WindowsJFrameProcess;

/**
 * The window controller for if the OS is Windows.
 */
public class MicrosoftWindowsController {
    /**
     * The JFrame process.
     */
    private final WindowsJFrameProcess winJFP;

    /**
     * Create a new window controller.
     *
     * @param winJFP The JFrame process.
     */
    public MicrosoftWindowsController(WindowsJFrameProcess winJFP) {
        this.winJFP = winJFP;
    }

    /**
     * Enable the window snap layout state for operating systems of Windows 11 or higher.
     */
    public void enableSnapLayout() {
        winJFP.setTempIgnoreHitTest(true);
        winJFP.setHitTestResult(Constants.HTZOOM);
    }

    /**
     * Disable the window snap layout state for operating systems of Windows 11 or higher.
     */
    public void disableSnapLayout() {
        winJFP.setTempIgnoreHitTest(false);
    }
}
