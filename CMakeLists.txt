cmake_minimum_required(VERSION 3.0)
project(Exolix)

find_package(LLVM REQUIRED CONFIG)

message(STATUS "Found LLVM ${LLVM_PACKAGE_VERSION}")
message(STATUS "Using LLVMConfig.cmake in: ${LLVM_DIR}")

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_C_STANDARD 23)

file(GLOB_RECURSE SOURCES "exolixc/*.cxx" "exolixc/*.hxx")

include_directories(exolixc/includes)
include_directories(${LLVM_INCLUDE_DIR})

add_definitions(${LLVM_DEFINITIONS})

add_executable(exolixc ${SOURCES})
target_include_directories(exolixc PUBLIC exolixc/includes)

llvm_map_components_to_libnames(llvm_libs support core irreader)
target_link_libraries(exolixc ${llvm_libs})
