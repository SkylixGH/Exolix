# Set CMake version
cmake_minimum_required (VERSION 2.8)
project (ElixorFramework)

# Set the version configuration
set (ElixorFramework_VERSION_MAJOR 1)
set (ElixorFramework_VERSION_MINOR 0)
set (ElixorFramework_VERSION_PATCH 0)
set (ElixorFramework_VERSION_TAG "dev")

# Add the core library
add_subdirectory (ElixorCore)
include_directories(ElixorCore ElixorCore/Source)

# Scratch testing files
add_executable (ElixorFramework ElixorCore Scratch.cxx)
target_link_libraries (ElixorFramework ElixorCore)
