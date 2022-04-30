package net.skylix.elixor.desktop.local.windows.jna;

import com.sun.jna.platform.win32.User32;

public interface User32Dwm extends User32 {
    void DwmSetWindowAttribute(HWND hWnd, int dwAttribute, RECT pvAttribute, int cbAttribute);
    HRESULT DwmExtendFrameIntoClientArea(HWND hWnd, MARGINS pMarInset);
    int DWMWA_EXTENDED_FRAME_BOUNDS = 0x0020;
    int WM_ACTIVATE = 0x0006;
    int WA_ACTIVE = 1;
}
