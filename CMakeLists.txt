cmake_minimum_required(VERSION 3.0)
project(Exolix)

include_directories(inc)

file(GLOB_RECURSE SOURCE_FILES src/*.cc inc/*.hh)
add_library(Exolix SHARED ${SOURCE_FILES})

add_subdirectory(exa)
