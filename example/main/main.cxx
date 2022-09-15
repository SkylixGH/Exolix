#include <exolix>

using namespace exolix;

int main() {
    exolix_initConsole();

    ColorRgb rgb {83, 255, 83};
    std::string ansi = TerminalColor::rgbToAnsi(rgb);

    Console::write(ansi + "Hello, World!");

    return 0;
}
