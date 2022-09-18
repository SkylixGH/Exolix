#include <exolix>
#include <vector>

using namespace exolix;

/**
 * A terminal editor written with the Exolix software
 * framework.
 * @return The process exit code.
 */
int main() {
    exolix_initConsole();

    ConsoleKeyboard kb;
    Console::clear();

    Console::write(TerminalColor::hexToAnsi(ColorHex("ff6070"), true) + TerminalColor::hexToAnsi(ColorHex("000")) + "Terminal Editor::['Made with Exolix']\n" + TerminalColor::reset);

    std::vector<std::string> lines = {
        "Hello First Line",
        "Hello Second Line",
        ""
    };

    auto render = [&] (int line, int col) {
        Console::setCursorPos({ 0, 2 });
        Console::setCursorBarVisible(false);
        Console::write("Line: " + std::to_string(line) + " Col: " + std::to_string(col) + " \n");

        int lineNum = 0;
        for (auto &line : lines) {
            lineNum++;

            Console::clearLine();
            Console::write(TerminalColor::hexToAnsi(ColorHex("666")) + std::to_string(lineNum) + "  |  " + TerminalColor::hexToAnsi(ColorHex("fff")) + line + "\n");
        }

        // move the cursor to the correct position
        Console::setCursorPos({ col + 7, line + 3 });
        Console::setCursorBarVisible(true);
    };

    int line = 0;
    int col = 0;

    kb.setAnsiListener([&] (char value, bool down, unsigned int code) {
        if (!down) return;

        // if arrow keys
        if (code == 37) {
            col--;
        } else if (code == 38) {
            line--;
        } else if (code == 39) {
            col++;
        } else if (code == 40) {
            line++;
        }

        if (line < 0) line = 0;
        if (col < 0) col = 0;

        if (col > lines[line].size()) {
            col = 0;
            line++;
        }

        if (line > lines.size() - 1) line = lines.size() - 1;

        bool backspace = code == 8;

        if (backspace) {
            if (col == 0) {
                if (line == 0) {
                    render(line, col);
                    return;
                };

                line--;
                col = lines[line].size();
                lines[line] += lines[line + 1];
                lines.erase(lines.begin() + line + 1);
            } else {
                col--;
                lines[line].erase(lines[line].begin() + col);
            }
        } else {
            if (value == '\r') {
                lines.insert(lines.begin() + line + 1, lines[line].substr(col));
                lines[line] = lines[line].substr(0, col);
                line++;
                col = 0;
            } else if (value == '\t') {
                lines[line].insert(lines[line].begin() + col, ' ');
                col++;
            } else if (value != 0) {
                lines[line].insert(lines[line].begin() + col, value);
                col++;
            } else {
                render(line, col);
                return;
            }
        }

        render(line, col);
    });

    render(line, col);
    kb.block();

    return 0;
}
