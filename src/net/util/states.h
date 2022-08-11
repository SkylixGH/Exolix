#pragma once

namespace exolix::net::util {
    enum class JobState {
        READY,
        ENABLING,
        DISABLING,
        OFF
    };
}
