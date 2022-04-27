package net.skylix.elixor.desktop.local.windows.jna;

import com.sun.jna.platform.win32.User32;

public interface User32Ex extends User32 {
    int GWLP_WNDPROC = -4;
    LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, WindowProc winProc);
    LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, LONG_PTR winProc);
    LRESULT CallWindowProc(LONG_PTR proc, HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam);
}
