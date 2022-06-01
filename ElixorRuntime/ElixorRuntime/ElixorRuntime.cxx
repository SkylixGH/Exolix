#include <GLFW/glfw3.h>
#include <vulkan/vulkan.hpp>
#include <iostream>
#include <stdlib.h>

using namespace std;

class ElixorRuntime {
public:
    GLFWwindow * window;
    VkInstance * instance;

    void Init() {
        glfwInit();
    }

    void setupWindow() {
        window = glfwCreateWindow(1200, 600, "Vulkan Demo", nullptr, nullptr);
        glfwShowWindow(window);
    }

    void startLoop() {
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
        }
    }

    void setVulkan() {
        auto * appInfo = new VkApplicationInfo();

        appInfo->sType = VK_STRUCTURE_TYPE_APPLICATION_INFO;
        appInfo->pApplicationName = "Vulkan Demo";
        appInfo->applicationVersion = VK_MAKE_VERSION(1, 0, 0);
        appInfo->pEngineName = "No Engine";
        appInfo->engineVersion = VK_MAKE_VERSION(1, 0, 0);
        appInfo->apiVersion = VK_API_VERSION_1_0;

        auto * createInfo = new VkInstanceCreateInfo();

        createInfo->sType = VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
        createInfo->pApplicationInfo = appInfo;

        uint32_t glfwExtensionCount = 0;
        const char ** glfwExtensions = glfwGetRequiredInstanceExtensions(&glfwExtensionCount);

        createInfo->enabledExtensionCount = glfwExtensionCount;
        createInfo->ppEnabledExtensionNames = glfwExtensions;

        createInfo->enabledLayerCount = 0;

        auto result = vkCreateInstance(createInfo, nullptr, instance);

        if (result != VK_SUCCESS) {
            cout << "Failed to create instance ERROR = " << result << endl;
            exit(1);
        }

        cout << "Extensions: " << glfwExtensionCount << endl;
    }

    ElixorRuntime() {
        Init();
        setupWindow();

        setVulkan();

        startLoop();
    }

    ~ElixorRuntime() {
        glfwTerminate();
    }
};

int main() {
    auto * rt = new ElixorRuntime();
}