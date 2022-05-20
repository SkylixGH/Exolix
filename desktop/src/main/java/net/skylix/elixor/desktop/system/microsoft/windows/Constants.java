package net.skylix.elixor.desktop.system.microsoft.windows;

/**
 * Win32 constants.
 */
public class Constants {
    /**
     * Win32 event constant SC_RESTORE.
     */
    public static final int SC_RESTORE = 0xF120;

    /**
     * Win32 event constant WM_NCCALCSIZE.
     */
    public static final int WM_NCCALCSIZE = 0x0083;

    /**
     * Win32 event constant WM_NCHITTEST.
     */
    public static final int WM_NCHITTEST = 0x0084;

    /**
     * Win32 event constant SIZE_MAXIMIZED.
     */
    public static final int SIZE_MAXIMIZED = 2;

    /**
     * For when the mouse is on the border of a non-resizable window.
     */
    public static final int HTBORDER = 18;

    /**
     * This allows for window dragging and acts as the maximize button
     */
    public static final int HTZOOM = 9;

    /**
     * Windows native title bar area for dragging.
     */
    public static final int HTCAPTION = 2;

    /**
     * Disable the window and play the native error sound.
     */
    public static final int HTERROR = -2;

    /**
     * Client area, where the main app is.
     */
    public static final int HTCLIENT = 1;

    /**
     * The top left region hit test message.
     */
    public static final int HTTOPLEFT = 13;

    /**
     * The top right region hit test message.
     */
    public static final int HTTOPRIGHT = 14;

    /**
     * The bottom left region hit test message.
     */
    public static final int HTBOTTOMLEFT = 16;

    /**
     * The bottom right region hit test message.
     */
    public static final int HTBOTTOMRIGHT = 17;

    /**
     * The left border hit test message.
     */
    public static final int HTLEFT = 10;

    /**
     * The right border hit test message.
     */
    public static final int HTRIGHT = 11;

    /**
     * The top border hit test message.
     */
    public static final int HTTOP = 12;

    /**
     * The bottom border hit test message.
     */
    public static final int HTBOTTOM = 15;

    /**
     * Win32 message for when the mouse leaves the window.
     */
    public static final int WM_MOUSELEAVE = 0x02A3;
}
