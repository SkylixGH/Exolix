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

    Console::write(
        TerminalColor("Exolix")
            .setBlink(true)
            ->setBold(false)
            ->setUnderline(true)
            ->render()
        + " Framework!\n"
    );

    MyWidget widget;
    auto runRes = TerminalWidgetRuntime::run(widget);

    if (runRes.isError) {
        switch (runRes.getError()) {
            case TerminalWidgetRuntimeErrors::WIDGET_ALREADY_RUNNING:
                Console::write("Widget already running!\n");
                break;
        }
    }

    AppProcess::block();
    return 0;
}
