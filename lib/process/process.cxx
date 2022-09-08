/*
 * Class for handling the Exolix process.
 * Detects the user system and sets the appropriate paths.
 */

#include "process.hxx"
#include <iostream>
#include <exception>

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
            CloseHandle(hToken);
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

    void Process::panic(const std::string &reason) {
        std::cerr << "The program ran into an error that could not be recovered.\n";
        std::cerr << "The program will soon terminate:\n";
        std::cerr << "\n    Error: " << reason << "\n\n";

        throw std::runtime_error("Program exiting with reason code: 1");
    }
}
