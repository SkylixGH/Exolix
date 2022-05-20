package net.skylix.elixor.desktop.system.microsoft.windows;

import com.sun.jna.platform.win32.User32;

/**
 * The User32 API extended with more functionality.
 */
public interface User32Ex extends User32 {
    /**
     * Win32 attribute GWLP_WNDPROC.
     */
    int GWLP_WNDPROC = -4;

    /**
     * Set a window long property pointer.
     * 
     * @param hWnd The window handle.
     * @param nIndex The index of the property.
     * @param winProc The window process.
     * 
     * @return The long pointer.
     */
    LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, WindowProc winProc);

    /**
     * Set a window long property pointer.
     * 
     * @param hWnd The window handle.
     * @param nIndex The index of the property.
     * @param winproc The window process long pointer.
     * 
     * @return The long pointer.
     */
    LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, LONG_PTR winProc);

    /**
     * Call the window proess.
     * 
     * @param proc The window long process.
     * @param hwnd The window handle.
     * @param uMsg The window message ID.
     * @param wParam The first window message parameter.
     * @param lParam The second window message parameter.
     * 
     * @return The LRESULT.
     */
    LRESULT CallWindowProc(LONG_PTR proc, HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam);
}
