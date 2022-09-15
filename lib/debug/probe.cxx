#include "probe.hxx"

#include <iostream>

namespace exolix {
    DebugProbe::DebugProbe() {
    }

    DebugProbe::~DebugProbe() {
    }

    Result<std::nullptr_t, DebugProbeErrors> DebugProbe::launch() {
        return Ok(nullptr);
    }

    Result<std::nullptr_t, DebugProbeErrors> DebugProbe::stop() {
        return Ok(nullptr);
    }

    void DebugProbeDevice::setProbe(exolix::DebugProbe &device) {
        this->probe = &device;
    }
}
