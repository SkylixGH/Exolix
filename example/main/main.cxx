#include <exolix>

using namespace exolix;

class MyWidget : public TerminalXtWidget {
public:

};

int main() {
//    MyWidget widget;
    exolix_initConsole();

    Console::write(
        TerminalColor("Exolix")
            .setFg(ColorHex("000"))
            ->setBk(ColorHex("fff"))
            ->render()
        + " Framework!\n" + TerminalColor::reset
    );

    while (getchar() != 'q') {}
    return 0;
}
