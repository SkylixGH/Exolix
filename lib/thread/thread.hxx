#pragma once

#include <thread>
#include <functional>
#include <exolix.hxx>

namespace exolix {
    enum class SleepUnit {
        NANOSECONDS,
        MILLISECONDS,
        SECONDS,
        MINUTES,
        HOURS,
        DAYS,
        WEEKS,
        YEARS
    };

    enum class ThreadErrors {
        THREAD_ALREADY_ONLINE
    };

    typedef Error<ThreadErrors> ThreadException;

    class Thread {
    private:
        bool running = false;
        bool hasStartedBefore = false;

        std::thread *thread {};
        std::function<void()> threadBody;

    public:
        explicit Thread(std::function<void()> body);
        ~Thread();

        void start();
        void block();
        void detach();

        bool canBlock();

        void startAndBlock();
        void startAndDetach();

        static void sleep(SleepUnit unit, long long value);
    };
}