#pragma once

namespace exolix {
    /**
     * A class used for creating debug probes, these are
     * devices that can be used in debug builds to help
     * see what a class, server, or other implementation is
     * currently busy at, storing, and more!
     */
     class DebugProbe {
     public:
         /**
          * Create a new debug probe.
          */
         explicit DebugProbe();
     };
}
