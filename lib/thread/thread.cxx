#include "thread.hxx"
#include "../process/process.hxx"
#include <chrono>

namespace exolix {
    Thread::Thread(std::function<void()> body):
        threadBody(std::move(body)) {
    }

    Thread::~Thread() {
        if (!running) {
            delete thread;
            return;
        }

        Process::panic("Thread deletion called while thread had an active executor");
    }

    void Thread::start() {
        if (running || hasStartedBefore)
            throw ThreadException(ThreadErrors::THREAD_ALREADY_ONLINE, "The thread is already online, or was started previously and cannot be restarted");

        hasStartedBefore = true;
        running = true;

        thread = new std::thread([this] () {
            threadBody();
            running = false;
        });
    }

    void Thread::block() {
        if (canBlock() && thread != nullptr) thread->join();
    }

    void Thread::detach() {
        if (canBlock() && thread != nullptr) thread->detach();
    }

    bool Thread::canBlock() {
        if (!running) return false;
        if (thread == nullptr) return false;

        return thread->joinable();
    }

    void Thread::startAndBlock() {
        start();
        block();
    }

    void Thread::startAndDetach() {
        start();
        detach();
    }

    void Thread::sleep(exolix::SleepUnit unit, long long value) {
        switch (unit) {
            case SleepUnit::NANOSECONDS:
                std::this_thread::sleep_for(std::chrono::nanoseconds(value));
                break;

            case SleepUnit::MILLISECONDS:
                std::this_thread::sleep_for(std::chrono::milliseconds(value));
                break;

            case SleepUnit::SECONDS:
                std::this_thread::sleep_for(std::chrono::seconds(value));
                break;

            case SleepUnit::MINUTES:
                std::this_thread::sleep_for(std::chrono::minutes(value));
                break;

            case SleepUnit::HOURS:
                std::this_thread::sleep_for(std::chrono::hours(value));
                break;

            case SleepUnit::DAYS:
                std::this_thread::sleep_for(std::chrono::days(value));
                break;

            case SleepUnit::WEEKS:
                std::this_thread::sleep_for(std::chrono::weeks(value));
                break;

            case SleepUnit::YEARS:
                std::this_thread::sleep_for(std::chrono::years(value));
                break;
        }
    }
}
