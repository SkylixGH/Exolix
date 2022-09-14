#pragma once

#include <string>

namespace exolix {
    /**
     * A class for manipulating path strings and more.
     */
    class Path {
    public:
        /**
         * Get the path of where this current executable is located.
         * @return The path of the executable.
         */
        static std::string getExecutablePath();

        /**
         * Join a group of paths together. This is very similar to NodeJS's path.join().
         * @param basePath The base path.
         * @param paths The paths to join.
         */
        static std::string join(std::string basePath, std::string paths...);
    };
}
