package net.skylix.elixor.desktop.local.windows;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.W32APIOptions;

import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.local.windows.jna.MARGINS;
import net.skylix.elixor.desktop.local.windows.jna.User32Ex;
import net.skylix.elixor.desktop.local.windows.jna.User32Dwm;

import javax.swing.*;

import java.awt.event.WindowListener;

import static com.sun.jna.platform.win32.WinUser.*;
import static net.skylix.elixor.desktop.local.windows.jna.User32Dwm.WA_ACTIVE;
import static net.skylix.elixor.desktop.local.windows.jna.User32Dwm.WM_ACTIVATE;

public class WindowsJFrameProcess implements WinUser.WindowProc {
    private static final int SC_RESTORE = 0xF120;
    private static final int WM_NCCALCSIZE = 0x0083;
    private static final int WM_NCHITTEST = 0x0084;
    private static final int SIZE_MAXIMIZED = 2;
    private final int renderedTitleBarHeight;
    private final boolean useCustomTitleBarHitTest;

    private HWND hWnd;
    private final User32Ex INSTANCE;
    private final User32Dwm INSTANCEDwm;
    private BaseTSD.LONG_PTR definedWindowProcess;
    private final ModJFrame frame;

    public WindowsJFrameProcess(boolean customHitTest, int titleBarHeightForHitTest, ModJFrame frame) {
        renderedTitleBarHeight = titleBarHeightForHitTest == 0 ? -10 : titleBarHeightForHitTest;
        useCustomTitleBarHitTest = customHitTest;
        this.frame = frame;

        INSTANCE = Native.load("user32", User32Ex.class, W32APIOptions.DEFAULT_OPTIONS);
        INSTANCEDwm = Native.load("dwmapi", User32Dwm.class, W32APIOptions.DEFAULT_OPTIONS);
    }

    public final void initializeProcess(WinDef.HWND hWnd) {
        if (!useCustomTitleBarHitTest) {
            return;
        }

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
        int controlBoxWidth = 135;
        int iconWidth = 27;
        int extraLeftReservedWidth = 0;
        int extraRightReservedWidth = 0;
        int maximizedWindowFrameThickness = 10;
        int frameResizeBorderThickness = 4;
        int frameBorderThickness = 1;

        int borderOffset = 10;
        int borderThickness = 4;

        POINT pointMouse = new POINT();
        RECT rectWindow = new RECT();

        User32.INSTANCE.GetCursorPos(pointMouse);
        User32.INSTANCE.GetWindowRect(hWnd, rectWindow);

        int uRow = 1;
        int uColumn = 1;

        boolean frameOnResizeBorder = false;
        boolean frameOnFrameDrag = false;
        boolean frameOnIcon = false;

        if (
                pointMouse.y >= rectWindow.top
                        && pointMouse.y < rectWindow.top + renderedTitleBarHeight + borderOffset
        ) {
            // If the top is being resized
            frameOnResizeBorder = (pointMouse.y < (rectWindow.top + borderThickness));

            if (!frameOnResizeBorder) {
                frameOnIcon = (pointMouse.y <= rectWindow.top + renderedTitleBarHeight)
                        && (pointMouse.x > rectWindow.left)
                        && (pointMouse.x < (rectWindow.left + iconWidth + borderOffset));

                if (!frameOnIcon) {
                    frameOnFrameDrag = (pointMouse.y <= rectWindow.top + renderedTitleBarHeight + borderOffset)
                            && (pointMouse.x < (rectWindow.right - (controlBoxWidth + borderOffset + extraRightReservedWidth)))
                            && (pointMouse.x > (rectWindow.left + iconWidth + borderOffset + extraLeftReservedWidth));
                }
            }

            uRow = 0;
        } else if (
                pointMouse.y < rectWindow.bottom
                        && pointMouse.y >= rectWindow.bottom - borderThickness
        ) {
            // The bottom is being resized
            uRow = 2;
        }

        if (
                pointMouse.x >= rectWindow.left
                        && pointMouse.x < rectWindow.left + borderThickness
        ) {
            // The left size is being resized
            uColumn = 0;
        } else if (
                pointMouse.x < rectWindow.right
                        && pointMouse.x >= rectWindow.right - borderThickness
        ) {
            // The right size is being resized
            uColumn = 2;
        }

        final int htTopLeft = 13;
        final int htTop = 12;
        final int htCaption = 2;
        final int htTopRight = 15;
        final int htLeft = 10;
        final int htNoWhere = 0;
        final int htRight = 11;
        final int htBottomLeft = 16;
        final int htBottom = 15;
        final int htBottomRight = 17;
        final int htSystemMenu = 3;

        int[][] hitTestsAll = {
                {
                        htTopLeft,
                        frameOnResizeBorder ? htTop : frameOnIcon ? htSystemMenu : frameOnFrameDrag ? htCaption : htNoWhere, htTopRight
                },
                {
                        htLeft,
                        htNoWhere,
                        htRight
                },
                {
                        htBottomLeft,
                        htBottom,
                        htBottomRight
                }
        };

        return new LRESULT(hitTestsAll[uRow][uColumn]);
    }

    private void applyMargins(HWND hWnd) {
        MARGINS margins = new MARGINS();
        margins.cxLeftWidth = 0;
        margins.cxRightWidth = 0;
        margins.cyTopHeight = 0;
        margins.cyBottomHeight = 1;

        INSTANCEDwm.DwmExtendFrameIntoClientArea(hWnd, margins);
    }

    @Override
    public final WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        LRESULT result;

        if (!useCustomTitleBarHitTest) {
            result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
            return result;
        }

        switch (uMsg) {
            case WM_NCCALCSIZE -> {
                applyMargins(hWnd);
                return new LRESULT(0);
            }

            case WM_ACTIVATE -> {
                applyMargins(hWnd);
                return new LRESULT(0);
            }

            case WM_NCHITTEST -> {
                result = borderLessHitTest(hWnd, uMsg, wParam, lParam);

                if (result.intValue() == new LRESULT(0).intValue()) {
                    return INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                }

                return result;
            }

            case WM_DESTROY -> {
                INSTANCE.SetWindowLongPtr(hWnd, User32Ex.GWLP_WNDPROC, definedWindowProcess);
                return new LRESULT(0);
            }

            case WM_SIZE -> {
                if (wParam.intValue() == SIZE_MAXIMIZED) {
                    frame.triggerMaximized();
                } else {
                    frame.triggerUnMaximized();
                }

                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }

            case WM_CLOSE -> {
                frame.triggerClosing();

                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }

            default -> {
                result = INSTANCE.CallWindowProc(definedWindowProcess, hWnd, uMsg, wParam, lParam);
                return result;
            }
        }
    }

    public final void maximize() {
        User32.INSTANCE.ShowWindow(hWnd, SW_MAXIMIZE);
    }

    public final void minimize() {
        User32.INSTANCE.ShowWindow(hWnd, SW_MINIMIZE);
    }
}
