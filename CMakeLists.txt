cmake_minimum_required(VERSION 3.20)
project(Exolix CXX)

include_directories(include)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_C_STANDARD 20)

file(GLOB_RECURSE Exolix_SOURCE_FILES lib/*.cxx lib/*.hxx)
add_library(exolix STATIC ${Exolix_SOURCE_FILES})

install(TARGETS exolix DESTINATION bin)
install(FILES include/exolix.hxx DESTINATION include)
