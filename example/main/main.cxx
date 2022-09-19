#include <exolix>

using namespace exolix;

class W : public TerminalXtWidget {
public:
    void handleKeyPress(const exolix::DriverKeyboardEvent &event) override {
        printf("Key pressed: %d\n", event.id);
    }
};

int main() {
    W w;
    w.start();

    w.block();
    return 0;
}
