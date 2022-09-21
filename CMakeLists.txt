project(SkylixExolix)
cmake_minimum_required(VERSION 3.2)

set(CMAKE_CXX_STANDARD 17)

# Add project files
file(GLOB_RECURSE SkylixExolix_LIB_FILES "lib/*.cxx")
file(GLOB_RECURSE SkylixExolix_INCLUDE_FILES "lib/*.hxx")
add_library(skylixExolix ${SkylixExolix_LIB_FILES} ${SkylixExolix_INCLUDE_FILES})
include_directories(include)

# Write to include file
file(WRITE include/exolix "#pragma once\n\n")
foreach(file ${SkylixExolix_INCLUDE_FILES})
    file(APPEND include/exolix "#include \"${file}\"\n")
endforeach()

# Add examples
file(GLOB SkylixExolix_EXAMPLES_LIST RELATIVE ${CMAKE_CURRENT_SOURCE_DIR}/example ${CMAKE_CURRENT_SOURCE_DIR}/example/*)
foreach(example ${SkylixExolix_EXAMPLES_LIST})
    message(STATUS "Exolix adding example: ${example}")
    add_subdirectory(example/${example})
endforeach()

# Install Exolix
install(TARGETS skylixExolix DESTINATION lib)
install(FILES include/exolix DESTINATION include)
install(FILES cmake/SkylixExolixConfig.cmake DESTINATION cmake)
