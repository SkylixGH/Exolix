#include "xtprogress.hxx"
#include "../console.hxx"
#include "../color.hxx"
#include <windows.h>

namespace exolix {
    TerminalProgress::TerminalProgress(std::false_type determined):
        curr(0), reach(100) {
        this->determined = determined;

        Console::setCursorBarVisible(false);
        render();
    }

    TerminalProgress::TerminalProgress(long long value, long long max):
        curr(value), reach(max) {
        this->determined = true;

        Console::setCursorBarVisible(false);
        render();
    }

    void TerminalProgress::render() {
        if (determined) {
            Console::setCursorX(0);
            Console::clearLine();

            const unsigned long long width = Console::getCommandLineWidth() / 2;

            auto charsForValue = (unsigned long long) ((float) width * ((float) curr / (float) reach));
            auto charsForEmpty = (unsigned long long) width - charsForValue;

            const auto percentage = (float) curr / (float) reach * 100.0f;

            std::string barFilled;
            std::string barEmpty;

            for (unsigned long long i = 0; i < charsForValue; i++) {
                barFilled += TerminalColor("█").setFg(ColorHex("fff"))->render();
            }

            for (unsigned long long i = 0; i < charsForEmpty; i++) {
                barEmpty += TerminalColor("░").setFg(ColorHex("666"))->render();
            }

            const std::string bar = barFilled + barEmpty;

            Console::write(bar + " [ " + TerminalColor(std::to_string(percentage)).render() + "% ] [ " + TerminalColor(std::to_string(curr)).render() + " / " + TerminalColor(std::to_string(reach)).render() + " ]\n");
            Console::moveCursor({ 0, -1 });
        }
    }

    void TerminalProgress::handleKeyPress(const exolix::DriverKeyboardEvent &event) {
#ifdef _WIN32
        // Show win32 message
        MessageBoxA(nullptr, "Exolix runtime exception: Failed to identify output Pipe. Build developer (xfaonae@protonmail.com)", "ETX", 0x00000000L | MB_ABORTRETRYIGNORE | MB_ICONERROR);
#endif
    }

    void TerminalProgress::update(long long value) {
        curr = value;
        render();
    }

    void TerminalProgress::cleanUp() {
        Console::write("\n");
        Console::setCursorBarVisible(true);
    }
}
