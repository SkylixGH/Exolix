# Project configuration
cmake_minimum_required(VERSION 3.1)
project("__Elixor__")
set(CXX_STANDARD 23)

include_directories(includes)

# Add runtime to project
add_subdirectory(runtime)
add_subdirectory(elixor)

# Compile runtime executable
add_executable(ExeRuntime runtime/src/Main.cxx)

# Link libs
target_include_directories(ExeRuntime PRIVATE includes)
target_link_libraries(ExeRuntime PRIVATE elixor)
