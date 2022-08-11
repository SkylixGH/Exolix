cmake_minimum_required(VERSION 3.2)
project(Exolix)

set(CMAKE_CXX_STANDARD 23)

include_directories(include)
file(GLOB_RECURSE SOURCE_FILES src/*.cc src/*.h)

add_library(exolix STATIC ${SOURCE_FILES})
add_subdirectory(example)
