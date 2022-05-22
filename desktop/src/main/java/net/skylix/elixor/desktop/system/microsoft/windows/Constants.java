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

    /**
     * Mouse event.
     */
    public static final int WM_MOUSEMOVE = 0x0200;

    /**
     * The mouse left button.
     */
    public static final int MK_LBUTTON = 0x0001;

    /**
     * The mouse right button.
     */
    public static final int MK_RBUTTON = 0x0002;

    /**
     * The mouse middle button.
     */
    public static final int MK_MBUTTON = 0x0004;

    /**
     * The mouse first extra button.
     */
    public static final int MK_XBUTTON1 = 0x0008;

    /**
     * The mouse second extra button.
     */
    public static final int MK_XBUTTON2 = 0x0010;

    /**
     * The mouse wheel up.
     */
    public static final int MK_WHEELUP = 0x0800;

    /**
     * The mouse wheel down.
     */
    public static final int MK_WHEELDOWN = 0x1000;

    /**
     * The mouse wheel left.
     */
    public static final int MK_WHEELLEFT = 0x2000;

    /**
     * The mouse wheel right.
     */
    public static final int MK_WHEELRIGHT = 0x4000;

    /**
     * The mouse wheel horizontal.
     */
    public static final int MK_HWHEEL = 0x8000;

    /**
     * The mouse left button.
     */
    public static final int WM_LBUTTONDOWN = 0x0201;

    /**
     * The mouse left button.
     */
    public static final int WM_LBUTTONUP = 0x0202;

    /**
     * The mouse left button.
     */
    public static final int WM_LBUTTONDBLCLK = 0x0203;

    /**
     * The mouse right button.
     */
    public static final int WM_RBUTTONDOWN = 0x0204;

    /**
     * The mouse right button.
     */
    public static final int WM_RBUTTONUP = 0x0205;

    /**
     * The mouse right button.
     */
    public static final int WM_RBUTTONDBLCLK = 0x0206;

    /**
     * The mouse middle button.
     */
    public static final int WM_MBUTTONDOWN = 0x0207;

    /**
     * The mouse middle button.
     */
    public static final int WM_MBUTTONUP = 0x0208;
}
