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
        int runs = 1002;

        while (true) {
            runs--;
            std::this_thread::sleep_for(std::chrono::seconds(1));
            auto ran = random();

            Console::setCursorPos({ 10, 10 });
            Console::clearLine();

            Console::write("Runs: " + std::to_string(runs));
        }
    }
};

int main() {
    exolix_initConsole();

    DebugProbe probe;
    MyServer server;

    server.setProbe(probe);
    server.loop();

    return 0;
}
