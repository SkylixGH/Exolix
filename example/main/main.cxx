#include <exolix>

using namespace exolix;

int main() {
    exolix_initConsole();

    Logger::info("Hello World! App running");
    Logger::success("Success message logged to the terminal");
    Logger::error("Error message logged to the terminal");
    Logger::warn("Warning message logged to the terminal");

    TerminalProgress p(TerminalProgressMode::UNDETERMINED);

    p.start();

    // wait 3 seconds
    std::this_thread::sleep_for(std::chrono::seconds(5));
    p.setDeterminedState(true);

    for (int i = 0; i <= 100; i++) {
        p.update(i);
        std::this_thread::sleep_for(std::chrono::nanoseconds(1000));

        if (i == 100) {
            p.setEndMessage("Finished this main job, ready for 2?");
            p.stop();
        }
    }

    p.block();

    TerminalProgress x;

    x.start();

    for (int i = 0; i <= 100; i++) {
        x.update(i);
        std::this_thread::sleep_for(std::chrono::milliseconds(10));

        if (i == 100) {
            x.setEndMessage("Finished this second job, looks like were all done for progress bars today!");
            x.stop();
        } else if (i > 90) {
            std::this_thread::sleep_for(std::chrono::milliseconds(100));
        } else if (i > 70) {
            std::this_thread::sleep_for(std::chrono::milliseconds(30));
        }
    }

    x.block();

    Console::write("77x Finished Heavy Job\n");

    AppProcess::block();
    return 0;
}
