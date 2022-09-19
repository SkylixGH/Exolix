#include <exolix>

using namespace exolix;

enum class g_Entity {
    NONE,
    ENTITY_X,
    ENTITY_O
};

class TicTacToe : public TerminalXtWidget {
private:
    g_Entity board[3][3] = {
            {g_Entity::NONE, g_Entity::NONE, g_Entity::NONE},
            {g_Entity::NONE, g_Entity::NONE, g_Entity::NONE},
            {g_Entity::NONE, g_Entity::NONE, g_Entity::NONE}
    };

    g_Entity currentPlayer;

    short int x = 0;
    short int y = 0;

    void enforce() {
        if (x > 2) {
            x = 2;
        } else if (x < 0) {
            x = 0;
        }

        if (y > 2) {
            y = 2;
        } else if (y < 0) {
            y = 0;
        }
    }

    void x_render() {
        Console::clear();

        Console::write("Tic Tac Toe in Exolix!\n");
        Console::write("Mode: 2 player\n");

        if (currentPlayer == g_Entity::ENTITY_X) {
            Console::write("Current player: X\n");
        } else {
            Console::write("Current player: O\n");
        }

        Console::write("\n");
        Console::setCursorBarVisible(false);

        for (auto row = 0; row < 3; row++) {
            Console::write("|  ");

            for (auto col = 0; col < 3; col++) {
                if (board[row][col] == g_Entity::NONE) {
                    Console::write("-  ");
                } else if (board[row][col] == g_Entity::ENTITY_X) {
                    Console::write("X  ");
                } else {
                    Console::write("O  ");
                }
            }

            Console::write("|\n");
        }

        // Move CLI cursor to where user cursor is
        enforce();

        int headerLines = 4;

        Console::returnCursor();

        // This was needed due to the way the board was rendered.
        // This could be replaced with a better positioning method.
        if (x == 0) {
            Console::setCursorX(2);
        } else if (x == 1) {
            Console::setCursorX(6);
        } else if (x == 2) {
            Console::setCursorX(10);
        }

        Console::setCursorY(y + headerLines);
        Console::setCursorBarVisible(true);
    }

public:
    explicit TicTacToe(g_Entity startingPlayer) {
        switch (startingPlayer) {
            case g_Entity::ENTITY_X:
                currentPlayer = g_Entity::ENTITY_X;
                break;

            case g_Entity::ENTITY_O:
                currentPlayer = g_Entity::ENTITY_O;
                break;

            case g_Entity::NONE:
                currentPlayer = g_Entity::ENTITY_X;
                break;
        }

        x_render();
    }

    void handleKeyPress(const exolix::DriverKeyboardEvent &event) override {
        const short int rightArrow = 39;
        const short int leftArrow = 37;
        const short int upArrow = 38;
        const short int downArrow = 40;

        const short int enter = 13;

        if (event.pressed)
            switch (event.id) {
                case rightArrow:
                    x++;
                    break;

                case leftArrow:
                    x--;
                    break;

                case upArrow:
                    y--;
                    break;

                case downArrow:
                    y++;
                    break;

                case enter:
                    if (board[y][x] == g_Entity::NONE) {
                        board[y][x] = currentPlayer;

                        if (currentPlayer == g_Entity::ENTITY_X) {
                            currentPlayer = g_Entity::ENTITY_O;
                        } else {
                            currentPlayer = g_Entity::ENTITY_X;
                        }
                    }
                    break;
            }

        enforce();
        x_render();
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

    TicTacToe game(g_Entity::NONE);
    auto runRes = TerminalWidgetRuntime::run(game);

    if (runRes.isError) {
        switch (runRes.getError()) {
            case TerminalWidgetRuntimeErrors::WIDGET_ALREADY_RUNNING:
                Console::write("Widget already running!\n");
                break;
        }
    }

    TerminalWidgetRuntime::block();
    return 0;
}
