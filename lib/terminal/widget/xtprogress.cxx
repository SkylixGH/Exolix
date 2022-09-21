#include "xtprogress.hxx"
#include "../console.hxx"
#include "../color.hxx"
#include <windows.h>
#include <string>

namespace exolix {
    const std::string frames[21] = {
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "██████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░░░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░░░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████░",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█████",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░███",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█",
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"
    };

    TerminalProgress::TerminalProgress(TerminalProgressMode mode):
        curr(0), reach(100), currentAnimationFrame(0), animeThread(nullptr) {
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


    TerminalProgress::TerminalProgress(long long value, long long max):
        curr(value), reach(max), currentAnimationFrame(0), animeThread(nullptr) {
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
                barFilled += TerminalColor("█").setFg(ColorHex("fff"))->render();
            }

            for (unsigned long long i = 0; i < charsForEmpty; i++) {
                barEmpty += TerminalColor("░").setFg(ColorHex("fff"))->render();
            }

            const std::string bar = barFilled + barEmpty;

            Console::write("Progress | " + bar + " [ " + TerminalColor(std::to_string(percentage)).render() + "% ] [ " + TerminalColor(std::to_string(curr)).render() + " / " + TerminalColor(std::to_string(reach)).render() + " ]\n");
        } else {
            currentAnimationFrame++;
            if (currentAnimationFrame > 20) {
                currentAnimationFrame = 0;
            }

            std::string frame = TerminalColor(frames[currentAnimationFrame]).setFg(ColorHex("fff"))->render();
            Console::write("Waiting | " + frame + " [ " + TerminalColor("...").render() + " ]\n");
        }

        Console::moveCursor({ 0, -1 });
    }

    void TerminalProgress::handleKeyPress(const exolix::DriverKeyboardEvent &event) {}

    void TerminalProgress::update(long long value) {
        curr = value;
        render();
    }

    void TerminalProgress::cleanUp() {
        Console::write("\n");
        Console::setCursorBarVisible(true);
    }

    void TerminalProgress::setDeterminedState(bool enabled) {
        animeThread = new std::thread([this, &enabled] () {
            determined = enabled;

            while (!determined) {
                std::this_thread::sleep_for(std::chrono::milliseconds(50));
                render();
            }
        });

        animeThread->detach();
        render();
    }
}
