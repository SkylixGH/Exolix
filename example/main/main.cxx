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
            .setBlink(true)
            ->setBold(false)
            ->setUnderline(true)
            ->render()
        + " Framework!\n"
    );

    while (getchar() != 'q') {}
    return 0;
}
