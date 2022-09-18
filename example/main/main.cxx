#include <exolix>

using namespace exolix;

class MyWidget : public TerminalXtWidget {
public:

};

int main() {
//    MyWidget widget;
    exolix_initConsole();

    Console::setCursorPos({ 2, 3 });
    Console::write("Hello\n");

    Console::setCursorX(2);
    Console::write("Hello");

    while (true) {}

    return 0;
}
