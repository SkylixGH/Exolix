#include <exolixs>

using namespace ex;

int main() {
    exolix_initConsole();

    TerminalConsoleWin32Impl console;

    console.setKeyListener([] (DriverKeyboardEvent &event) {
        printf("Key pressed: %c, Pressed: %d, ID: %d, Ctrl: %d, Shift: %d, Alt: %d, Meta: %d\n",
            event.value, event.pressed, event.id, event.lCtrl, event.lShift, event.lAlt, event.meta);
    });

    console.block();

    return 0;
}
