#pragma once

#include <functional>
#include <future>
#include "../exolix.h"
#include "../number/types.h"

using namespace std::chrono_literals;

namespace exolix {
    /**
     * An enumerated list containing units for time.
     */
    enum class TimeUnit {
        Nanosecond,
        Microsecond,
        Millisecond,
        Second,
        Minute,
        Hour
    };

    /**
     * An enumeration of possible error returned
     * by functions in the thread class.
     */
    enum class ThreadErrors {
        /**
         * No errors occurred.
         */
         Ok,

        /**
         * This error is usually returned when you
         * tried to block a thread when it was already
         * blocked before.
         */
        ThreadAlreadyBlocked,

        /**
         * This error is usually returned when you
         * try to block a thread that is not active.
         */
        ThreadNotActive
    };

    class ThreadErrorsStr {
    public:
        static std::string toString(ThreadErrors error) {
            switch (error) {
                case ThreadErrors::Ok:
                    return "Ok";

                case ThreadErrors::ThreadAlreadyBlocked:
                    return "The thread was already being blocked and cannot be blocked again";

                case ThreadErrors::ThreadNotActive:
                    return "A thread operation was requested but its not active";
            }
        }
    };

    /**
     * C++ thread handler class that can instantiate, handle and manage
     * threads.
     */
    class Thread {
    private:
        /**
         * The actual future thread instance with async.
         */
        std::future<void> future;

        /**
         * Whether the thread was ever blocked before.
         */
         bool wasBlocked = false;

    public:
        /**
         * Create a new thread.
         * @param threadMain The thread's main function
         */
        explicit Thread(std::function<void()> threadMain);

        ~Thread();

        /**
         * Check to see if the thread is still live
         * and actively running.
         * @return If the thread is running.
         */
        bool isActive();

        /**
         * Wait till the thread is inactive.
         * @return The error code if an error occurred.
         */
        ThreadErrors block();

        /**
         * Call a sleep event.
         * @param value The time value to wait for in the unit provided.
         * @param type The time format used for waiting.
         */
        static void wait(u64 value, TimeUnit type);

        // TODO: Add wait until to wait until a certain time
    };
}
