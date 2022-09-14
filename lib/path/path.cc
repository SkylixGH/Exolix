#include "path.h"

#if defined(_WIN32)
    #include <windows.h>
#endif

namespace exolix {
    std::string Path::getExecutablePath() {
#if defined(__linux__)
        char result[PATH_MAX];
        ssize_t count = readlink("/proc/self/exe", result, PATH_MAX);
        return std::string(result, (count > 0) ? count : 0);
#elif defined(__APPLE__)
        char result[PATH_MAX];
        uint32_t size = sizeof(result);
        if (_NSGetExecutablePath(result, &size) == 0)
            return std::string(result);
        else
            return std::string();
#elif defined(_WIN32)
        char result[MAX_PATH];
        return std::string(result, GetModuleFileName(NULL, result, MAX_PATH));
#endif
    }

    std::string Path::join(std::string basePath, std::string paths, ...) {
        return "";
    }
}
