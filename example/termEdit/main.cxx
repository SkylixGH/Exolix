#include <exolixs>

using namespace ex;

int main() {
    exolix_initConsole();
    Console::write("Loading IDE...\n");

    std::string fd = "Hello World!";
    Console::setCursorPos({ 0, 0 });
    Console::clear();

    ConsoleKeyboard kbd;

    kbd.setAnsiListener([&fd] (char value) {
        bool backspace = value == 8;

        if (!backspace) {
            fd += value;
        } else {
            fd.pop_back();
        }

        // Render
        Console::clear();

        Console::write("Quick Text Editor: [ Chars = " + std::to_string(fd.size()) + " ] V = NULL\n");
        Console::setCursorPos({ 0, 2 });
        Console::write(fd);
    });

    kbd.block();

    Console::clear();
    Console::write("Editor Crash Detected\n");
    return 0;
}
