cmake_minimum_required(VERSION 3.2)
project(ElixorFramework)

add_subdirectory(elixor)
include_directories(elixor)

add_executable(Scratch1 scratch/main.cxx)
target_link_libraries(Scratch1 Elixor)
