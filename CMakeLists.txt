# Set CMake version
cmake_minimum_required (VERSION 3.2)
project (ElixorFramework)
include(FetchContent)

# C++ version 23 min
set (CMAKE_CXX_STANDARD 23)

# Set the version configuration
set (ElixorFramework_VERSION_MAJOR 1)
set (ElixorFramework_VERSION_MINOR 0)
set (ElixorFramework_VERSION_PATCH 0)
set (ElixorFramework_VERSION_TAG "dev")

# Add the core library
add_subdirectory (ElixorCore)
include_directories(ElixorCore ElixorCore/ElixorCore)

# Scratch testing files
add_subdirectory(Scratch)

# Make Scratch.cxx.
add_executable(ScratchEx Scratch/Scratch/Scratch.cxx)

# If Linux enable X11 with -lX11
if (UNIX)
    set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -lX11")
endif()

# Make ElixorRuntime.cxx.
add_executable(ElixorRuntimeEx ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx)

# Find dependencies
add_subdirectory("${CMAKE_CURRENT_SOURCE_DIR}/Externals/KhronosGroup/Vulkan-Hpp/Vulkan-Headers")
add_subdirectory("${CMAKE_CURRENT_SOURCE_DIR}/Externals/KhronosGroup/Vulkan-Hpp/glfw")

target_include_directories(ElixorRuntimeEx PRIVATE Externals/KhronosGroup/Vulkan-Hpp/glfw/include)
target_include_directories(ElixorRuntimeEx PRIVATE Externals/SFML/SFML/include)

target_link_libraries(ElixorRuntimeEx glfw)
find_package(Vulkan REQUIRED FATAL_ERROR)

# Join dependencies
target_link_libraries(ScratchEx ElixorCore)
target_link_libraries(ElixorRuntimeEx Vulkan::Vulkan)
