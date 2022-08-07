cmake_minimum_required(VERSION 3.0)
project(Exolix)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_C_STANDARD 23)

add_executable(exolixc exolixc/main.cxx)
target_include_directories(exolixc PUBLIC exolixc/includes)

include_directories(exolixc/includes)
