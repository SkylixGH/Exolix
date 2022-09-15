#include "exolixs"
#include <string>
#include <random>
#include <chrono>
#include <thread>

using namespace ex;

class MyServer : public DebugProbeDevice {
public:
    double random() {
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_real_distribution<> dis(0, 1);

        return dis(gen);
    }

    void loop() {
        while (true) {
            std::this_thread::sleep_for(std::chrono::seconds(1));
            auto ran = random();

            probe->setProp("Number", ran);
        }
    }
};

int main() {
    DebugProbe probe;
    MyServer server;

    server.setProbe(probe);
    server.loop();

    return 0;
}
