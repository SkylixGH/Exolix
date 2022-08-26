#include "process.hxx"

#if defined(__linux__) || defined(__APPLE__)
#include <unistd.h>
#elif _WIN32
#include <windows.h>
#endif

namespace exolix {
    bool Process::hasRoot() {
#if defined(__linux__) || defined(__APPLE__)
        return getuid() == 0;
#elif _WIN32
        bool funRet = false;
        HANDLE hToken = NULL;

        if (OpenProcessToken(GetCurrentProcess(), TOKEN_QUERY, &hToken)) {
            TOKEN_ELEVATION elevation;
            DWORD cbSize = sizeof(TOKEN_ELEVATION);

            if (GetTokenInformation(hToken, TokenElevation, &elevation, sizeof(elevation), &cbSize)) {
                funRet = elevation.TokenIsElevated;
            }
        }

        if (hToken) {
            CloseHandle(HToken);
        }

        return funRet;
#endif
    }

    OperatingSystems Process::getOs() {
#ifdef __ANDROID__
        return OperatingSystems::ANDROID;
#elif __linux__
        return OperatingSystems::LINUX;
#elif __APPLE__

#include <TargetConditionals.h>
#if TARGET_IPHONE_SIMULATOR
        return OperatingSystems::IOS;
#elif TARGET_OS_MACCATALYST
        return OperatingSystems::MACCAT;
#elif TARGET_OS_IPHONE
        return OperatingSystems::IOS;
#elif TARGET_OS_MAC
        return OperatingSystems::MAC;
#else
#error "Unknown Apple platform"
#endif

#elif _WIN32
        return OperatingSystems::WINDOWS;
#else
        return OperatingSystems::OTHER;
#endif
    }
}
