#include "probe.hxx"
#include <iostream>
#include "../terminal/console.hxx"

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

    void DebugProbe::setPropInt(const std::string &keyName, const long long &value) {
#if defined(_DEBUG)
        props[keyName] = std::to_string(value);
        render();
#endif
    }

    void DebugProbe::setPropDouble(const std::string &keyName, const double &value) {
#if defined(_DEBUG)
        props[keyName] = std::to_string(value);
        render();
#endif
    }

    void DebugProbe::render() {
        Console::clear();

        for (auto &prop: props) {
            std::cout << prop.first << ": " << prop.second << std::endl;
        }
    }

    void DebugProbeDevice::setProbe(exolix::DebugProbe &device) {
        this->probe = &device;
    }
}
