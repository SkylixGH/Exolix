project(Skylix_Exolix)
cmake_minimum_required(VERSION 3.2)

set(CMAKE_CXX_STANDARD 20)

# Add project files
file(GLOB_RECURSE Skylix_Exolix_LIB_FILES "lib/*.cxx")
file(GLOB_RECURSE Skylix_Exolix_INCLUDE_FILES "lib/*.hxx")
add_library(skylixExolix ${Skylix_Exolix_LIB_FILES} ${Skylix_Exolix_INCLUDE_FILES})
include_directories(include)

# Write to include file
file(WRITE include/exolix "#pragma once\n\n")
foreach(file ${Skylix_Exolix_INCLUDE_FILES})
    file(APPEND include/exolix "#include \"${file}\"\n")
endforeach()

file(WRITE include/exolixs "#pragma once\n\n")
foreach(file ${Skylix_Exolix_INCLUDE_FILES})
    file(APPEND include/exolixs "#include \"${file}\"\n")
endforeach()
file(APPEND include/exolixs "\nnamespace ex {\n    using namespace exolix;\n}\n")

# Add examples
file(GLOB SkylixExolix_EXAMPLES_LIST RELATIVE ${CMAKE_CURRENT_SOURCE_DIR}/example ${CMAKE_CURRENT_SOURCE_DIR}/example/*)
foreach(example ${SkylixExolix_EXAMPLES_LIST})
    message(STATUS "Exolix adding example: ${example}")
    add_subdirectory(example/${example})
endforeach()
