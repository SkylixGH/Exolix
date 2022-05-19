package net.skylix.elixor.desktop.system.microsoft.windows;

import com.sun.jna.platform.win32.User32;

/**
 * Win32 desktop window manager interface.
 */
public interface User32Dwm extends User32 {
    /**
     * Set a property for the desktop window manager.
     * 
     * @param hWnd The window handle.
     * @param dwAttribute Attribute DW.
     * @param pvAttribute Attribute PV.
     * @param cbAttribute Attribute CB.
     */
    void DwmSetWindowAttribute(HWND hWnd, int dwAttribute, RECT pvAttribute, int cbAttribute);

    /**
     * Extend the client area into the window frame.
     * 
     * @param hWnd The window handle.
     * @param pMarInset The margin inset.
     * @return The HRESULT from Win32.
     */
    HRESULT DwmExtendFrameIntoClientArea(HWND hWnd, Margins pMarInset);

    /**
     * Desktop window manager, extend into frame bounds message ID.
     */
    int DWMWA_EXTENDED_FRAME_BOUNDS = 0x0020;

    /**
     * Window manager activate event from the Win32 event API.
     */
    int WM_ACTIVATE = 0x0006;

    /**
     * Window manager active event from the Win32 event API. 
     */
    int WA_ACTIVE = 1;
}
