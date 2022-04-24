package net.skylix.elixor.elixorFX;

import com.sun.javafx.tk.TKStage;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import javafx.stage.Stage;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.qt.Qt5Widgets.QApplication;
import org.bytedeco.qt.Qt5Widgets.QTextEdit;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Method;

import static com.sun.jna.platform.win32.WinUser.*;

public class ElixorFX {
    private static IntPointer argc;
    private static PointerPointer argv;

    /**
     * Extended client area example
     */
    public static void init() {
        ModJFrame frame = new ModJFrame();
        frame.setVisible(true);

        HWND hwin = frame.getHWND();

//        if (message == WM_ACTIVATE)
//        {
//            // Extend the frame into the client area.
//            MARGINS margins;
//
//            margins.cxLeftWidth = LEFTEXTENDWIDTH;      // 8
//            margins.cxRightWidth = RIGHTEXTENDWIDTH;    // 8
//            margins.cyBottomHeight = BOTTOMEXTENDWIDTH; // 20
//            margins.cyTopHeight = TOPEXTENDWIDTH;       // 27
//
//            hr = DwmExtendFrameIntoClientArea(hWnd, &margins);
//
//            if (!SUCCEEDED(hr))
//            {
//                // Handle the error.
//            }
//
//            fCallDWP = true;
//            lRet = 0;
//        }
        // we will implement the code above

        // Listen for messages
        MSG msg = new MSG();
        int WM_ACTIVATE = 0x0006;

        while (User32.INSTANCE.GetMessage(msg, hwin, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessage(msg);

            if (msg.message == WM_QUIT) {
                break;
            }

            if (msg.message == WM_ACTIVATE) {
                System.out.println("WM_ACTIVATE");

                // Extend the frame into the client area.
                int cxLeftWidth = 8;
                int cxRightWidth = 8;
                int cyBottomHeight = 20;
                int cyTopHeight = 27;

                int hr = User32.INSTANCE.DwmExtendFrameIntoClientArea(hwin, cxLeftWidth, cxRightWidth, cyBottomHeight, cyTopHeight);
            }
        }
    }

    public static class ModJFrame extends JFrame {
        public ModJFrame() {
            super();
        }

        public HWND getHWND() {
            HWND hwnd = new HWND();
            hwnd.setPointer(Native.getComponentPointer(this));

            return hwnd;
        }
    }
}
