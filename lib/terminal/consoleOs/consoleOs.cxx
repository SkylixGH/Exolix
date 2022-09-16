#include "consoleOs.hxx"

namespace exolix {
    TerminalConsoleImpl::TerminalConsoleImpl() {
        thread = new std::thread([this] () {
            listener();
        });
    }

    TerminalConsoleImpl::~TerminalConsoleImpl() {
        dispose();

        if (thread->joinable())
            thread->join();

        delete thread;
    }

    void TerminalConsoleImpl::listener() {}

    void TerminalConsoleImpl::setKeyListener(std::function<void(DriverKeyboardEvent &event)> listener) {
        this->keyListener = listener;
    }

    void TerminalConsoleImpl::dispose() {
        disposed = true;
    }

    bool TerminalConsoleImpl::isDisposed() {
        return disposed;
    }

    void TerminalConsoleImpl::block() {
        if (thread->joinable())
            thread->join();
    }
}
