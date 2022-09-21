#include "xtprogress.hxx"
#include "../console.hxx"
#include "../color.hxx"
#include "../logger/backend.hxx"
#include <windows.h>
#include <string>

namespace exolix {
    const std::string frames[24] = {
            "| ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "| █░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "| ████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "/ ████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "/ ░████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "/ ░░░░████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ░░░░░░░░░░░████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ░░░░░░░░░░░░░░░░░████████░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ░░░░░░░░░░░░░░░░░░░░░░░░█████████░░░░░░░░░░░░░░░░░",
            "\\ ░░░░░░░░░░░░░░░░░░░░░░░░░░███████████░░░░░░░░░░░░░",
            "\\ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░█████████████░░░░░░░░░",
            "\\ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████████████░",
            "| ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████████",
            "| ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████",
            "| ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████████",
            "/ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░████",
            "/ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██",
            "/ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ██████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "- ░░░░░░░░█████████████████░░░░░░░░░░░░░░░░░░░░░░░░░",
            "\\ ░░░░░░░░░░░░░░░░░░░░██████████████████████░░░░░░░░",
            "\\ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████████",
            "\\ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░███",

    };

    TerminalProgress::TerminalProgress(TerminalProgressMode mode) :
            curr(0), reach(100), currentAnimationFrame(0), animeThread(nullptr), animeRunning(false),
            endMessage(std::nullopt) {
        switch (mode) {
            case TerminalProgressMode::UNDETERMINED:
                setDeterminedState(false);

                Console::setCursorBarVisible(false);
                render();
                break;

            case TerminalProgressMode::DETERMINED:
                setDeterminedState(true);

                Console::setCursorBarVisible(false);
                render();
                break;
        }
    }


    TerminalProgress::TerminalProgress(long long value, long long max) :
            curr(value), reach(max), currentAnimationFrame(0), animeThread(nullptr), animeRunning(false),
            endMessage(std::nullopt) {
        setDeterminedState(true);

        Console::setCursorBarVisible(false);
        render();
    }

    TerminalProgress::~TerminalProgress() {
        stop();

        if (animeThread->joinable()) animeThread->join();
        delete animeThread;
    }

    void TerminalProgress::render() {
        Console::setCursorX(0);
        Console::clearLine();

        const unsigned long long width = 50;

        if (determined) {
            auto charsForValue = (unsigned long long) ((float) width * ((float) curr / (float) reach));
            auto charsForEmpty = (unsigned long long) width - charsForValue;

            const auto percentage = (float) curr / (float) reach * 100.0f;

            std::string barFilled;
            std::string barEmpty;

            for (unsigned long long i = 0; i < charsForValue; i++) {
                barFilled += TerminalColor("█", ColorRgb()).setFg(ColorHex("fff"))->render();
            }

            for (unsigned long long i = 0; i < charsForEmpty; i++) {
                barEmpty += TerminalColor("░", ColorRgb()).setFg(ColorHex("fff"))->render();
            }

            const std::string bar = barFilled + barEmpty;

            Console::write("Progress " + bar + " [ " + TerminalColor(std::to_string(percentage), ColorRgb()).render() +
                           "% ] [ " +
                           TerminalColor(
                                   std::to_string(curr), ColorRgb()).render() + " / " +
                           TerminalColor(
                                   std::to_string(reach), ColorRgb()).render() + " ]\n");
        } else {
            std::string frame = TerminalColor(frames[currentAnimationFrame], ColorRgb()).setFg(
                    ColorHex("fff"))->render();
            Console::write("Waiting " + frame + " [ " + TerminalColor("...", ColorRgb()).render() + " ]\n");
        }

        Console::moveCursor({0, -1});
    }

    void TerminalProgress::handleKeyPress(const exolix::DriverKeyboardEvent &event) {}

    void TerminalProgress::update(long long value) {
        curr = value;

        setDeterminedState(true);
        render();
    }

    void TerminalProgress::cleanUp() {
        if (!determined) setDeterminedState(true);

        if (endMessage.has_value()) {
            Console::clearLine();
            Console::write(
                    LoggerBackend::messageFormat("", "INFO:", ColorRgb{200, 200, 200}) + endMessage.value() +
                    " [ " + TerminalColor(std::to_string(curr),
                                          ColorRgb()).render() + " / " +
                    TerminalColor(
                            std::to_string(reach), ColorRgb()).render() + " ]\n");

            Console::setCursorBarVisible(true);
            return;
        }

        Console::write("\n");
        Console::setCursorBarVisible(true);
    }

    void TerminalProgress::setDeterminedState(bool enabled) {
        if (animeThread != nullptr) {
            animeRunning = false;
            if (animeThread->joinable()) animeThread->join();

            delete animeThread;
        }

        animeThread = new std::thread([this, &enabled]() {
            determined = enabled;
            animeRunning = enabled;

            while (!animeRunning) {
                std::this_thread::sleep_for(std::chrono::milliseconds(50));

                currentAnimationFrame++;
                if (currentAnimationFrame > 23) {
                    currentAnimationFrame = 0;
                }

                render();
            }
        });

        animeThread->detach();
        render();
    }

    void TerminalProgress::setEndMessage(std::optional<std::string> message) {
        endMessage = message;
    }
}
