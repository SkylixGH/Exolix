/*
 * Defined the available operating systems.
 */

#pragma once

#include <string>

namespace exolix {
    enum class OperatingSystems {
        LINUX,
        WINDOWS,
        MAC,
        ANDROID,
        IOS,
        OTHER
    };

    class Process {
    public:
        static bool hasRoot();

        static OperatingSystems getOs();

        static void panic(const std::string &reason);
    };
}
