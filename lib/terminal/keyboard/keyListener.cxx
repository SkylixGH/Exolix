#include <utility>

#include "keyListener.hxx"

namespace exolix {
    ConsoleKeyboard::ConsoleKeyboard() {
#ifdef _WIN32
        impl = new TerminalConsoleWin32Impl();

        DriverKeyboardEvent lastEvent;

        impl->setKeyListener([this, &lastEvent] (DriverKeyboardEvent &event) {
            if (lastEvent.lCtrl != event.lCtrl) {
                ctrlListener(event.lCtrl, DriverKeyboardSection::LEFT);
            }

            if (lastEvent.rCtrl != event.rCtrl) {
                ctrlListener(event.rCtrl, DriverKeyboardSection::RIGHT);
            }

            if (lastEvent.lAlt != event.lAlt) {
                altListener(event.lAlt, DriverKeyboardSection::LEFT);
            }

            if (lastEvent.rAlt != event.rAlt) {
                altListener(event.rAlt, DriverKeyboardSection::RIGHT);
            }

            if (lastEvent.lShift != event.lShift) {
                shiftListener(event.lShift, DriverKeyboardSection::LEFT);
            }

            if (lastEvent.rShift != event.rShift) {
                shiftListener(event.rShift, DriverKeyboardSection::RIGHT);
            }

            if (lastEvent.meta != event.meta) {
                metaListener(event.meta);
            }

            if (event.value != '\0') {
                ansiListener(event.value);
            }

            lastEvent = event;
        });
#endif
        // TODO: Other OS
    }

    ConsoleKeyboard::~ConsoleKeyboard() {
        dispose();
        delete impl;
    }

    void ConsoleKeyboard::setAnsiListener(std::function<void(char)> listener) {
        ansiListener = std::move(listener);
    }

    void ConsoleKeyboard::setAltListener(std::function<void(bool, DriverKeyboardSection)> listener) {
        altListener = std::move(listener);
    }

    void ConsoleKeyboard::setCtrlListener(std::function<void(bool, DriverKeyboardSection)> listener) {
        ctrlListener = std::move(listener);
    }

    void ConsoleKeyboard::setShiftListener(std::function<void(bool, DriverKeyboardSection)> listener) {
        shiftListener = std::move(listener);
    }

    void ConsoleKeyboard::setMetaListener(std::function<void(bool)> listener) {
        metaListener = std::move(listener);
    }

    void ConsoleKeyboard::block() {
        impl->block();
    }

    void ConsoleKeyboard::dispose() {
        impl->dispose();
    }
}
