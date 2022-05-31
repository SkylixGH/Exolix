#include <iostream>
#include <GLFW/glfw3.h>
#include <vulkan/vulkan.h>
#include <thread>
#include <chrono>
#include <vector>

using namespace std;

int main(int argc, char ** argv) {
    // Simple vulkan example

    glfwInit();
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
    GLFWwindow * window = glfwCreateWindow(800, 600, "Vulkan", nullptr, nullptr);

    VkApplicationInfo appInfo{};
    appInfo.sType = VK_STRUCTURE_TYPE_APPLICATION_INFO;
    appInfo.pApplicationName = "Hello Triangle";
    appInfo.applicationVersion = VK_MAKE_VERSION(1, 0, 0);
    appInfo.pEngineName = "No Engine";
    appInfo.engineVersion = VK_MAKE_VERSION(1, 0, 0);
    appInfo.apiVersion = VK_API_VERSION_1_0;

    VkInstanceCreateInfo createInfo{};
    createInfo.sType = VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
    createInfo.pApplicationInfo = &appInfo;

    uint32_t glfwExtensionCount = 0;
    const char** glfwExtensions;

    glfwExtensions = glfwGetRequiredInstanceExtensions(&glfwExtensionCount);

    createInfo.enabledExtensionCount = glfwExtensionCount;
    createInfo.ppEnabledExtensionNames = glfwExtensions;

    createInfo.enabledLayerCount = 0;

    VkInstance instance;

    VkResult result = vkCreateInstance(&createInfo, nullptr, &instance);

    if (vkCreateInstance(&createInfo, nullptr, &instance) != VK_SUCCESS) {
        throw std::runtime_error("failed to create instance!");
    }

    // Create the msot minimal vulak instance
//    VkInstance instance;
//    VkResult result = vkCreateInstance(nullptr, nullptr, &instance);
//    if (result != VK_SUCCESS) {
//        cout << "Failed to create vulkan instance" << endl;
//        return -1;
//    }

    glfwShowWindow(window);
    // Set resizable with hint
    glfwSetWindowAttrib(window, GLFW_RESIZABLE, GLFW_TRUE);

    while (!glfwWindowShouldClose(window)) {
        glfwPollEvents();
    }

    return 0;
}
