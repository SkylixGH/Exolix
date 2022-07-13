cmake_minimum_required(VERSION 3.2)
project(ElixorFramework)

set(CMAKE_CXX_STANDARD 23)

add_subdirectory(elixor)
include_directories(elixor)

add_executable(main scratch/main.cxx)
target_link_libraries(main Elixor)
