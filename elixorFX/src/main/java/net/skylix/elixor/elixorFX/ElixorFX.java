package net.skylix.elixor.elixorFX;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;
import com.sun.net.httpserver.Authenticator;
import net.skylix.elixor.elixorFX.jna.MARGINS;
import net.skylix.elixor.elixorFX.jni.WindowsJNI;

import javax.swing.*;

import static com.sun.jna.platform.win32.WinUser.*;

interface EUser32 extends User32 {
    EUser32 INSTANCE = Native.load("user32", EUser32.class, W32APIOptions.DEFAULT_OPTIONS);
    HRESULT DwmExtendFrameIntoClientArea(HWND hwnd, MARGINS pMarInset);
}

public class ElixorFX {
    /**
     * Extended client area example
     */
    public static void init() {
        ModJFrame frame = new ModJFrame();
        frame.setVisible(true);

        HWND hwin = frame.getHWND();
        System.out.println("Lol" + new WindowsJNI().bar());

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

        System.out.println("WM_ACTIVATE");

        // Extend the frame into the client area.
        MARGINS margins = new MARGINS() {{
            cxLeftWidth = 8;
            cxRightWidth = 8;
            cyBottomHeight = 20;
            cyTopHeight = 27;
        }};

//        WinNT.HRESULT hr = EUser32.INSTANCE.DwmExtendFrameIntoClientArea(hwin, margins);

        while (User32.INSTANCE.GetMessage(msg, hwin, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessage(msg);

            if (msg.message == WM_QUIT) {
                break;
            } else if (msg.message == WM_ACTIVATE) {}
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
