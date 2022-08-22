cmake_minimum_required(VERSION 3.2)
project(Exolix)

set(CMAKE_CXX_STANDARD 23)

include_directories(include)
file(GLOB_RECURSE SOURCE_FILES src/*.cc src/*.h)

add_library(exolix STATIC ${SOURCE_FILES})

# Add windows libraries
if (WIN32)
    target_link_libraries(exolix PUBLIC ws2_32)
endif()

# Add OpenSSL
find_package(OpenSSL REQUIRED)
target_link_libraries(exolix PUBLIC OpenSSL::SSL OpenSSL::Crypto)

add_subdirectory(example)
