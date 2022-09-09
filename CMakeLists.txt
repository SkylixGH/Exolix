cmake_minimum_required(VERSION 3.0)
project(ExolixFramework)

# Get all files
file(GLOB_RECURSE Exolix_SOURCE_FILES lib/*.cc lib/*.h)

# Add library files
add_library(Exolix STATIC ${Exolix_SOURCE_FILES})

# Set include directories
target_include_directories(Exolix PUBLIC include)

# Set compile options
set(CMAKE_CXX_STANDARD 20)

# Add examples
add_subdirectory(example/main)
