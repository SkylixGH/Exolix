#pragma once

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
    };
}
