#include "console.hxx"
#include <iostream>

#if defined(_WIN32)
    #define ENABLE_VIRTUAL_TERMINAL_PROCESSING 0x0004
    #include <windows.h>
#endif

namespace exolix {
    bool Console::supportsAnsi() {
#if defined(_WIN32)
        HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);
        if (hOut == INVALID_HANDLE_VALUE) {
            return false;
        }

        DWORD dwMode = 0;
        if (!GetConsoleMode(hOut, &dwMode)) {
            return false;
        }

        dwMode |= ENABLE_VIRTUAL_TERMINAL_PROCESSING;
        if (!SetConsoleMode(hOut, dwMode)) {
            return false;
        }

        return true;
#elif defined(__linux__) || defined(__APPLE__)
        // TODO: Process
#endif
    }

    void Console::clear() {
        write("\033[2J\033[1;1H");
        setCursorPos({ 0, 0 });
    }

    void Console::write(const std::string& text) {
        std::cout << text;
    }

    void Console::moveCursor(const exolix::ConsoleLocation &relativeLocation) {
        if (relativeLocation.y < 0) {
            write("\033[" + std::to_string(-relativeLocation.y) + "A");
        } else if (relativeLocation.y > 0) {
            write("\033[" + std::to_string(relativeLocation.y) + "B");
        }

        if (relativeLocation.x < 0) {
            write("\033[" + std::to_string(-relativeLocation.x) + "D");
        } else if (relativeLocation.x > 0) {
            write("\033[" + std::to_string(relativeLocation.x) + "C");
        }
    }

    void Console::setCursorPos(const exolix::ConsoleLocation &location) {
        if (location.y < 0) {
            write("\033[" + std::to_string(-location.y) + "A");
        } else if (location.y > 0) {
            write("\033[" + std::to_string(location.y) + "B");
        }

        if (location.x < 0) {
            write("\033[" + std::to_string(-location.x) + "D");
        } else if (location.x > 0) {
            write("\033[" + std::to_string(location.x) + "C");
        }
    }

    void Console::setCursorX(int x) {
        write("\033[" + std::to_string(x + 1) + "G");
    }

    void Console::setCursorY(int y) {
        write("\033[" + std::to_string(y + 1) + "d");
    }

    void Console::clearLine() {
        write("\033[2K");
    }

    void Console::setCursorBarVisible(bool visible) {
        if (visible) {
            write("\033[?25h");
        } else {
            write("\033[?25l");
        }
    }

    void exolix_initConsole() {
#if defined(_WIN32)
        HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);
        DWORD dwMode = 0;
        GetConsoleMode(hOut, &dwMode);
        dwMode |= ENABLE_VIRTUAL_TERMINAL_PROCESSING;
        SetConsoleMode(hOut, dwMode);
#endif

        // TODO: Linux and Mac

        Console::setCursorBarVisible(true);
    }
}
