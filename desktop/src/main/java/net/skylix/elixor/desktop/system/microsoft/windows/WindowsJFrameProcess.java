package net.skylix.elixor.desktop.system.microsoft.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.W32APIOptions;
import net.skylix.elixor.desktop.events.MouseEvent;
import net.skylix.elixor.desktop.events.MouseEventType;
import net.skylix.elixor.desktop.window.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.jna.platform.win32.WinUser.*;

/**
 * A JFrame window process for the Windows platform.
 */
public class WindowsJFrameProcess implements WinUser.WindowProc {
    /**
     * All the drag regions.
     */
    private final ArrayList<Point[]> dragRegions = new ArrayList<>();

    /**
     * All the drag regions to ignore.
     */
    private final ArrayList<Point[]> excludedDragRegions = new ArrayList<>();

    /**
     * Win32 instance,
     */
    private final User32Ex INSTANCE;
    /**
     * Win32 instance for the desktop window manager.
     */
    private final User32Dwm INSTANCEDwm;
    /**
     * The window frame for Java Swing.
     */
    private final JFrame frame;
    /**
     * The Elixor high level API.
     */
    private final Window window;
    /**
     * The current mouse button.
     */
    private int mouseButton = 0;
    /**
     * The window's handle.
     */
    private HWND hWnd;
    /**
     * If the mouse is pressed.
     */
    private boolean mousePressed = false;

    /**
     * The defined window process.
     */
    private BaseTSD.LONG_PTR definedWindowProcess;

    /**
     * The current hit test result.
     */
    private int hitTestResult = Constants.HTCLIENT;

    /**
     * Whether to ignore hit test calculation in the hit tester method.
     */
    private boolean tempIgnoreHitTest = false;

    /**
     * If the window is in its maximized state.
     */
    private boolean isMaximized = false;

    /**
     * Create a new windows JFrame process.
     *
     * @param frame  The Java Swing window frame.
     * @param window The Elixor high level API.
     */
    public WindowsJFrameProcess(JFrame frame, Window window) {
        this.frame = frame;
        this.window = window;

        INSTANCE = Native.load("user32", User32Ex.class, W32APIOptions.DEFAULT_OPTIONS);
        INSTANCEDwm = Native.load("dwmapi", User32Dwm.class, W32APIOptions.DEFAULT_OPTIONS);
    }

    public void addTitleBarDragRegion(Point[] region) {
        dragRegions.add(region);
    }

    public void removeTitleBarDragRegion(Point[] region) {
        dragRegions.remove(region);
    }

    public void addTitleBarExcludedDragRegion(Point[] region) {
        excludedDragRegions.add(region);
    }

    public void removeTitleBarExcludedDragRegion(Point[] region) {
        excludedDragRegions.remove(region);
    }

    public void initializeProcess(WinDef.HWND hWnd) {
        this.hWnd = hWnd;
        definedWindowProcess = INSTANCE.SetWindowLongPtr(hWnd, User32Ex.GWLP_WNDPROC, this);

        INSTANCE.SetWindowPos(
                hWnd,
                hWnd,
                0,
                0,
                0,
                0,
                SWP_NOMOVE | SWP_NOSIZE | SWP_NOZORDER | SWP_FRAMECHANGED
        );

        // add WS_EX_LAYERED
        INSTANCE.SetWindowLong(hWnd, User32Ex.GWL_EXSTYLE, INSTANCE.GetWindowLong(hWnd, User32Ex.GWL_EXSTYLE) | User32Ex.WS_EX_LAYERED);

        RECT bounds = new RECT();

        bounds.left = 0;
        bounds.top = 0;
        bounds.right = 0;
        bounds.bottom = 0;

        INSTANCEDwm.DwmSetWindowAttribute(
                hWnd,
                User32Dwm.DWMWA_EXTENDED_FRAME_BOUNDS,
                bounds,
                4
        );
    }

    private WinDef.LRESULT borderLessHitTest(HWND hWnd, int uMsg, WPARAM wParam, LPARAM lParam) {
        int frameResizeBorderThickness = isMaximized ? 0 : 10;

        POINT pointMouse = new POINT();
        RECT rectWindow = new RECT();

        User32.INSTANCE.GetCursorPos(pointMouse);
        User32.INSTANCE.GetWindowRect(hWnd, rectWindow);

        final int mouseX = pointMouse.x - rectWindow.left;
        final int mouseY = pointMouse.y - rectWindow.top;
        final int width = rectWindow.right - rectWindow.left;
        final int height = rectWindow.bottom - rectWindow.top;

        final MouseEvent event = new MouseEvent(mouseX, mouseY, mouseButton, mousePressed, MouseEventType.ANY);
        window.setVirtualMouseMeta(event);

        if (!tempIgnoreHitTest)
            if (
                // Top left corner resize 
                    mouseX >= 0
                            && mouseX < frameResizeBorderThickness
                            && mouseY >= 0
                            && mouseY < frameResizeBorderThickness
            ) {
                hitTestResult = Constants.HTTOPLEFT;
            } else if (
                // Top right corner resize
                    mouseX >= width - frameResizeBorderThickness
                            && mouseX < width
                            && mouseY >= 0
                            && mouseY < frameResizeBorderThickness
            ) {
                hitTestResult = Constants.HTTOPRIGHT;
            } else if (
                // Bottom left corner resize
                    mouseX >= 0
                            && mouseX < frameResizeBorderThickness
                            && mouseY >= height - frameResizeBorderThickness
                            && mouseY < height
            ) {
                hitTestResult = Constants.HTBOTTOMLEFT;
            } else if (
                // Bottom right corner resize
                    mouseX >= width - frameResizeBorderThickness
                            && mouseX < width
                            && mouseY >= height - frameResizeBorderThickness
                            && mouseY < height
            ) {
                hitTestResult = Constants.HTBOTTOMRIGHT;
            } else if (
                // Left border resize
                    mouseX >= 0
                            && mouseX < frameResizeBorderThickness
                            && mouseY >= frameResizeBorderThickness
                            && mouseY < height - frameResizeBorderThickness
            ) {
                hitTestResult = Constants.HTLEFT;
            } else if (
                // Right border resize
                    mouseX >= width - frameResizeBorderThickness
                            && mouseX < width
                            && mouseY >= frameResizeBorderThickness
                            && mouseY < height - frameResizeBorderThickness
            ) {
                hitTestResult = Constants.HTRIGHT;
            } else if (
                // Top border resize
                    mouseX >= frameResizeBorderThickness
                            && mouseX < width - frameResizeBorderThickness
                            && mouseY >= 0
                            && mouseY < frameResizeBorderThickness
            ) {
                hitTestResult = Constants.HTTOP;
            } else if (
                // Bottom border resize
                    mouseX >= frameResizeBorderThickness
                            && mouseX < width - frameResizeBorderThickness
                            && mouseY >= height - frameResizeBorderThickness
                            && mouseY < height
            ) {
                hitTestResult = Constants.HTBOTTOM;
            } else if (
                // Check drag regions
                    isInDragRegion(mouseX, mouseY)
            ) {
                hitTestResult = Constants.HTCAPTION;
            } else {
                hitTestResult = Constants.HTCLIENT;
            }

        return new LRESULT(hitTestResult);
    }

    private boolean isInDragRegion(int x, int y) {
        boolean result = false;

        final ArrayList<Point[]> dragRegionsClone = new ArrayList<>(dragRegions);
        final ArrayList<Point[]> excludedDragRegionsClone = new ArrayList<>(excludedDragRegions);

        for (Point[] region : dragRegionsClone) {
            try {
                if (
                        x >= region[0].x
                                && x <= region[1].x
                                && y >= region[0].y
                                && y <= region[1].y
                ) {
                    result = true;
                    break;
                }
            } catch (Exception e) {
                // Ignore
            }
        }

        for (Point[] region : excludedDragRegionsClone) {
            try {
                if (
                        x >= region[0].x
                                && x <= region[1].x
                                && y >= region[0].y
                                && y <= region[1].y
                ) {
                    result = false;
                    break;
                }
            } catch (Exception e) {
                // Ignore
            }
        }

        return result;
    }

    private void applyMargins(HWND hWnd) {
        Margins margins = new Margins();

        margins.cxLeftWidth = 0;
        margins.cxRightWidth = 0;
        margins.cyTopHeight = 0;
        margins.cyBottomHeight = 1;

        INSTANCEDwm.DwmExtendFrameIntoClientArea(hWnd, margins);
    }

    @Override
    public WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        LRESULT result;

        switch (uMsg) {
            case Constants.WM_NCCALCSIZE, User32Dwm.WM_ACTIVATE -> {
                applyMargins(hWnd);
                return new LRESULT(0);
            }

            case Constants.WM_NCHITTEST -> {
                result = borderLessHitTest(hWnd, uMsg, wParam, lParam);

                if (result.intValue() == new LRESULT(0).intValue()) {
                    return INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                }

                return result;
            }

            case Constants.WM_MOUSEMOVE -> {
                final int button = wParam.intValue();
                final int x = lParam.intValue() & 0xFFFF;
                final int y = lParam.intValue() >> 16;
                final boolean isPressed = button != 0;

                mouseButton = button;

                final MouseEvent event = new MouseEvent(x, y, button, isPressed, MouseEventType.MOUSE_MOVED);
                window.setVirtualMouseMeta(event);

                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }

            // all mouse buttons
            case Constants.WM_LBUTTONDOWN,
                    Constants.WM_LBUTTONUP,
                    Constants.WM_RBUTTONDOWN,
                    Constants.WM_RBUTTONUP,
                    Constants.WM_MBUTTONDOWN,
                    Constants.WM_MBUTTONUP -> {
                final int button = wParam.intValue();
                final int x = lParam.intValue() & 0xFFFF;
                final int y = lParam.intValue() >> 16;

                final MouseEventType causedBy = switch (uMsg) {
                    case Constants.WM_LBUTTONDOWN, Constants.WM_LBUTTONUP, Constants.WM_RBUTTONDOWN, Constants.WM_RBUTTONUP, Constants.WM_MBUTTONDOWN, Constants.WM_MBUTTONUP -> MouseEventType.MOUSE_BUTTON_INTERACT;
                    default -> MouseEventType.MOUSE_MOVED;
                };

                mouseButton = button;

                switch (uMsg) {
                    case Constants.WM_LBUTTONDOWN,
                            Constants.WM_RBUTTONDOWN,
                            Constants.WM_MBUTTONDOWN -> {
                        mousePressed = true;
                    }

                    case Constants.WM_LBUTTONUP,
                            Constants.WM_RBUTTONUP,
                            Constants.WM_MBUTTONUP -> {
                        mousePressed = false;
                    }
                }

                final MouseEvent event = new MouseEvent(x, y, button, mousePressed, causedBy);
                window.setVirtualMouseMeta(event);

                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }

            case WM_DESTROY -> {
                INSTANCE.SetWindowLongPtr(hWnd, User32Ex.GWLP_WNDPROC, definedWindowProcess);
                return new LRESULT(0);
            }

            case WM_SIZE -> {
                if (wParam.intValue() == Constants.SIZE_MAXIMIZED) {
                    applyMargins(hWnd);
                    isMaximized = true;
                } else {
                    applyMargins(hWnd);
                    isMaximized = false;
                }

                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }

            default -> {
                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }
        }
    }

    public void maximize() {
        User32.INSTANCE.ShowWindow(hWnd, SW_MAXIMIZE);
    }

    public void minimize() {
        User32.INSTANCE.ShowWindow(hWnd, SW_MINIMIZE);
    }

    public void closeWindowNative() {
        INSTANCE.ShowWindow(hWnd, SW_HIDE);
    }

    public void setHitTestResult(int hitTestResult) {
        this.hitTestResult = hitTestResult;
    }

    public void setTempIgnoreHitTest(boolean tempIgnoreHitTest) {
        this.tempIgnoreHitTest = tempIgnoreHitTest;
    }

    public void clearAllDragInfo() {
        dragRegions.clear();
        excludedDragRegions.clear();
    }
}
