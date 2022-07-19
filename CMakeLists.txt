# Elixor Framework
# Project Developed and Maintained by Skylix!
# Visit the project at https://skylix.net/projects/elixor/

# This program is free software: you can redistribute it and/or modify
# We hope to see what you build!

cmake_minimum_required(VERSION 3.2)
project(ElixorFramework)

set(CMAKE_CXX_STANDARD 23)
set(ELIXOR_ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})

add_subdirectory(elixor)
include_directories(elixor)

add_executable(main scratch/main.cxx)
target_link_libraries(main Elixor)
