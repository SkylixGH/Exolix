#include "path.h"
#include <vector>
#include "../string/deconstruction.h"
#include "../number/types.h"
#include <iostream>

#if defined(_WIN32)
    #include <windows.h>
#elif defined(__linux__)
    #include <linux/limits.h>
    #include <unistd.h>
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
        return std::string(result, GetModuleFileName(nullptr, result, MAX_PATH));
#endif
    }

    std::string Path::join(const std::string &path1, const std::string &path2) {
        std::vector<std::string> path1Parts = StringDeconstruction::split(path1, "/");
        std::vector<std::string> path2Parts = StringDeconstruction::split(path2, "/");
        std::vector<std::string> allParts;

        for (const std::string& part : path1Parts) {
            if (!part.empty())
                allParts.push_back(part);
        }

        for (const std::string& part : path2Parts) {
            if (!part.empty())
                allParts.push_back(part);
        }

        std::string result = "/";
        const i64 max = (i64) allParts.size();

        for (i64 i = 0; i < max; i++) {
            std::cout << "Part: " << allParts[i] << std::endl;

            result += allParts[i] + "/";
        }

        return result;
    }
}
