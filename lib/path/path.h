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
         * @param path1 The first path to join.
         * @param path2 The second path to join.
         */
        static std::string join(const std::string& path1, const std::string& path2);

        /**
         * Simplify a path string. Make the path as short as possible while
         * still keeping it lossless.
         * @param inputPath The path to simplify.
         * @return The simplified path.
         */
        static std::string simplify(const std::string &inputPath);
    };
}
