cmake_minimum_required(VERSION 3.0)
project(Exolix)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_C_STANDARD 23)

include_directories(exolixc/includes)

add_executable(exolixc exolixc/main.cxx)
target_include_directories(exolixc PUBLIC exolixc/includes)
