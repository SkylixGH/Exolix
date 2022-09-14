#include <chrono>
#include <thread>
#include "thread.h"

namespace exolix {
    Thread::Thread(std::function<void()> threadMain) {
        future = std::async(std::launch::async, threadMain);
    }

    Thread::~Thread() {
        if (isActive()) {
            future.get();
        }
    }

    bool Thread::isActive() {
        try {
            return future.wait_for(0ms) != std::future_status::ready;
        } catch (...) {
            return false;
        }
    }

    ThreadErrors Thread::block() {
        if (!isActive())
            return ThreadErrors::ThreadNotActive;

        if (wasBlocked)
            return ThreadErrors::ThreadAlreadyBlocked;

        try {
            future.get();
            return ThreadErrors::Ok;
        } catch (...) {
            return ThreadErrors::ThreadAlreadyBlocked;
        }
    }

    void Thread::wait(u64 value, exolix::TimeUnit type) {
        switch (type) {
            case TimeUnit::Nanosecond:
                std::this_thread::sleep_for(std::chrono::nanoseconds(value));
                break;

            case TimeUnit::Microsecond:
                std::this_thread::sleep_for(std::chrono::microseconds(value));
                break;

            case TimeUnit::Millisecond:
                std::this_thread::sleep_for(std::chrono::milliseconds(value));
                break;

            case TimeUnit::Second:
                std::this_thread::sleep_for(std::chrono::seconds(value));
                break;

            case TimeUnit::Minute:
                std::this_thread::sleep_for(std::chrono::minutes(value));
                break;

            case TimeUnit::Hour:
                std::this_thread::sleep_for(std::chrono::hours(value));
                break;

            default:
                break;
        }
    }

    bool Thread::blockedBefore() const {
        return wasBlocked;
    }
}
