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

    // Create the msot minimal vulak instance
    VkInstance instance;
    VkResult result = vkCreateInstance(nullptr, nullptr, &instance);
    if (result != VK_SUCCESS) {
        cout << "Failed to create vulkan instance" << endl;
        return -1;
    }

    return 0;
}
