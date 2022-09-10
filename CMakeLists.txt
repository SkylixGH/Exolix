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
set(Exolix_VERSION_MAJOR 1)
set(Exolix_VERSION_MINOR 0)
set(Exolix_VERSION_PATCH 0)
set(Exolix_VERSION ${Exolix_VERSION_MAJOR}.${Exolix_VERSION_MINOR}.${Exolix_VERSION_PATCH})

message("Exolix Version Detected: ${Exolix_VERSION}")

# Add examples
add_subdirectory(example/main)

# Add windows specific libraries
if (WIN32)
    target_link_libraries(Exolix PUBLIC ws2_32)
endif ()

# Add OpenSSL libraries
find_package(OpenSSL REQUIRED)
target_link_libraries(Exolix PUBLIC OpenSSL::SSL OpenSSL::Crypto)
