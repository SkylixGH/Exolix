# Set CMake version
cmake_minimum_required (VERSION 2.8)
project (ElixorFramework)

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

# Make ElixorRuntime.cxx.
add_executable(ElixorRuntimeEx ElixorRuntime/ElixorRuntime/ElixorRuntime.cxx)

# Find dependencies
#Add vulkan from ./Dependencies/KhronosGroup
add_subdirectory(Externals/glfw/glfw)
add_subdirectory(Externals/sfml/sfml)
add_subdirectory(Externals/KhronosGroup/Vulkan-Hpp)

# Join dependencies
target_link_libraries(ScratchEx ElixorCore)
target_link_libraries(ElixorRuntime vulkan)
