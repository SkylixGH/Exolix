#include "win32.hxx"

#ifdef _WIN32
    #include <windows.h>
    #include <iostream>
#endif

namespace exolix {
    void TerminalConsoleWin32Impl::listener() {
        printf("Listener started for win32\n");

        KEY_EVENT_RECORD keyDat;

        while (!isDisposed()) {
            DriverKeyboardEvent event {};

#ifdef _WIN32
            DWORD cc;
            INPUT_RECORD inRec;
            HANDLE h = GetStdHandle(STD_INPUT_HANDLE);

            if (h == nullptr) {
                // TODO: Error handling because not in CLI.
                throw std::runtime_error("Could not get console handle");
            }

            while (!isDisposed()) {
                ReadConsoleInput(h, &inRec, 1, &cc);

                if (
                    inRec.EventType == KEY_EVENT
                ) {
                    keyDat = (KEY_EVENT_RECORD &) inRec.Event;

                    event.id = keyDat.wVirtualKeyCode;
                    event.value = keyDat.uChar.AsciiChar;

                    if (keyDat.bKeyDown) {
                        event.pressed = true;
                    } else if (!keyDat.bKeyDown) {
                        event.pressed = false;
                    }

                    if (keyDat.dwControlKeyState & LEFT_CTRL_PRESSED) {
                        event.lCtrl = true;
                    } else {
                        event.lCtrl = false;
                    }

                    if (keyDat.dwControlKeyState & RIGHT_CTRL_PRESSED) {
                        event.rCtrl = true;
                    } else {
                        event.rCtrl = false;
                    }

                    if (keyDat.dwControlKeyState & LEFT_ALT_PRESSED) {
                        event.lAlt = true;
                    } else {
                        event.lAlt = false;
                    }

                    if (keyDat.dwControlKeyState & RIGHT_ALT_PRESSED) {
                        event.rAlt = true;
                    } else {
                        event.rAlt = false;
                    }

                    if (keyDat.dwControlKeyState & SHIFT_PRESSED) {
                        event.lShift = true;
                    } else {
                        event.lShift = false;
                    }

                    if (keyDat.dwControlKeyState & SHIFT_PRESSED) {
                        event.rShift = true;
                    } else {
                        event.rShift = false;
                    }

                    if (keyDat.dwControlKeyState & ENHANCED_KEY) {
                        event.meta = true;
                    } else {
                        event.meta = false;
                    }

                    keyListener(event);
                }
            }
#endif
        }
    }
}
