#include "searches.hxx"

namespace exolix {
    std::tuple<ColorRgb, long long> AlgorithmSearch::findClosestRgb(const std::vector<ColorRgb> &list, const exolix::ColorRgb &rgb) {
        ColorRgb closestRgb = list[0];
        long long biggestDistance = 1000;
        long long foundMatchIndex = 0;

        for (long long i = 0; i < list.size(); i++) {
            ColorRgb currentRgb = list[i];
            long long currentDistance = std::abs(currentRgb.red - rgb.red) + std::abs(currentRgb.green - rgb.green) + std::abs(currentRgb.blue - rgb.blue);

            if (currentDistance < biggestDistance) {
                biggestDistance = currentDistance;
                closestRgb = currentRgb;
                foundMatchIndex = i;
            }
        }

        return std::make_tuple(closestRgb, foundMatchIndex);
    }
}
