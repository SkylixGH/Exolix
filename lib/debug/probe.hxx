#pragma once

#include "../result/result.hxx"
#include <map>
#include <string>

namespace exolix {
    /**
     * An enumerated list of error codes for the debug probe.
     */
    enum class DebugProbeErrors {
        /**
         * The debug probe has already been launched. You must wait till it is
         * both not busy and not launched.
         */
        ALREADY_LAUNCHED,

        /**
         * The debug probe has not been launched. You must wait till it is
         * both not busy and launched.
         */
        NOT_LAUNCHED,

        /**
         * The debug probe is currently busy. Must wait till the probe is not
         * busy.
         */
        ERROR_BUSY,
    };

    /**
     * A class used for creating debug probes, these are
     * devices that can be used in debug builds to help
     * see what a class, server, or other implementation is
     * currently busy at, storing, and more!
     */
    class DebugProbe {
    private:
        /**
         * All of the rendering properties for debugging.
         */
        std::map<std::string, std::string> props{};

    public:
        /**
         * Create a new debug probe.
         */
        explicit DebugProbe();

        ~DebugProbe();

        /**
         * Launch the debug probe. This starts a file interface server
         * where you can read all the debug information. For this to be successful
         * you must ensure that the probe is not busy and not launched.
         * @return The result of the launch.
         */
        Result<std::nullptr_t, DebugProbeErrors> launch();

        /**
         * Stop the debug probe. For this to succeed the server must not be
         * busy and must be active.
         * @return The result of the stop.
         */
        Result<std::nullptr_t, DebugProbeErrors> stop();

        /**
         * Set a debug probe visualizer device property. This
         * will render a custom key pair value in the visualizer.
         * @param keyName The name of the key.
         * @param value The value of the key.
         */
        void setPropDouble(const std::string &keyName, const double &value);

        /**
         * Set a debug probe visualizer device property. This
         * will render a custom key pair value in the visualizer.
         * @param keyName The name of the key.
         * @param value The value of the key.
         */
        void setPropInt(const std::string &keyName, const long long &value);

        /**
         * Render the data to the screen.
         */
        void render();
    };

    /**
     * A class that should be used to extend another class
     * for support for debug probes.
     */
    class DebugProbeDevice {
    protected:
        /**
         * The debug probe.
         */
        DebugProbe *probe{};

    public:
        /**
         * Set the debug probe. This will start applying to the
         * new device immediately.
         * @param device The debug probe.
         */
        void setProbe(DebugProbe &device);
    };
}
