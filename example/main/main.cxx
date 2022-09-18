#include <exolix>

using namespace exolix;

class MyWidget : public TerminalXtWidget {
private:
    void handleKeyPress(const DriverKeyboardEvent &event) override {
        if (!event.pressed) return;
        Console::write("Key pressed: " + std::to_string(event.value) + "\n");
    }
};

int main() {
    exolix_initConsole();

    MyWidget widget;
//    TerminalWidgetRuntime::run(widget);

//    Console::write(
//        TerminalColor("Exolix")
//            .setBlink(true)
//            ->setBold(false)
//            ->setUnderline(true)
//            ->render()
//        + " Framework!\n"
//    );

    auto pid = AppProcess::getProcessId();
    Console::write("Process id: " + std::to_string(pid) + "\n");

    while (getchar() != 'q') {}
    return 0;
}
