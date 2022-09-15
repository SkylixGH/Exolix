#include <exolix>
#include <chrono>
#include <thread>

using namespace exolix;

int main() {
    exolix_initConsole();

    ColorHex hex("50ffab");
    ColorRgb rgb = hex.getRgb();

    std::cout << "Hex: " << hex.getHex() << std::endl;

    bool on = true;

    const char frames[4] = { '|', '/', '-', '\\' };
    short int frame = 0;

    while (true) {
        std::this_thread::sleep_for(std::chrono::milliseconds(100));
        Console::clearLine();
        Console::setCursorPos({ 0, 3 });

        Console::write(TerminalColor::hexToAnsi(ColorHex("fff")) + std::string(1, frames[frame]) + TerminalColor::hexToAnsi(ColorHex("999")) + " Loading");
        frame++;

        if (frame > 3) {
            frame = 0;
        }

        on = !on;
    }

    return 0;
}
