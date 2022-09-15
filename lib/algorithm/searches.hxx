#pragma once

#include "../color/colors.hxx"
#include <vector>
#include <tuple>

namespace exolix {
    /**
     * A class used for running search based algorithms.
     */
    class AlgorithmSearch {
    public:
        /**
         * This function is used for finding the closest RGB value
         * from a list of other rgb colors.
         * @param list The list of RGB colors to search through.
         * @param rgb The rgb color to find the closest match for.
         * @return A tuple containing the color first and the index of the color second.
         */
        static std::tuple<ColorRgb, long long> findClosestRgb(const std::vector<ColorRgb> &list, const ColorRgb &rgb);
    };
}
