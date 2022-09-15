#include <exolixs>

using namespace ex;

int main() {
    exolix_initConsole();

    ColorRgb red { 255, 10, 10 };
    ColorRgb blue { 10, 10, 255 };

    ColorRgb result = AlgorithmSearch::findClosestRgb({ red, blue }, { 255, 200, 55 });
    printf("Result: %d, %d, %d\n", result.red, result.green, result.blue);

    return 0;
}
