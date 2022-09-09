#include <exolix>
#include <iostream>

using namespace exolix;

int main() {
    Thread t1([] () {
        int i = 0;

        while (i < 3) {
            printf("Hello, world!\n");
            Thread::wait(1, TimeUnit::Second);

            i++;
        }
    });

    t1.block();
    auto re = t1.block();

    if (re != ThreadErrors::Ok) {
        std::cout << ("Error: " + ThreadErrorsStr::toString(re)) << std::endl;
        return 1;
    }

    printf("Done blocking\n");
    auto running = t1.isActive();

    if (running) {
        printf("Running");
    } else {
        printf("Dead");
    }

    return 0;
}