#pragma once

namespace exolix {
    class TlsManager {
    private:
        static bool openSslInitialized;

    public:
        static void safeInitialize();
        static void cleanUp();
    };
}
