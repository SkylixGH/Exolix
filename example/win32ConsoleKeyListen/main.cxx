#include <exolixs>

using namespace ex;

int main() {
    exolix_initConsole();

    TerminalConsoleWin32Impl console;

    console.setKeyListener([] (DriverKeyboardEvent &event) {
        printf("Key pressed: %d", event.id);
    });

    console.block();

    return 0;
}
