package net.skylix.elixor.desktop.local.windows.jna;

import com.sun.jna.platform.win32.User32;

public interface User32Dwm extends User32 {
    LRESULT DwmSetWindowAttribute(HWND hWnd, int dwAttribute, RECT pvAttribute, int cbAttribute);
    int DWMWA_EXTENDED_FRAME_BOUNDS = 0x0020;
}
