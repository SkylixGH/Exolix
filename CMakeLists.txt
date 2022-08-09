cmake_minimum_required(VERSION 3.0)
project(Exolix)

# Generate the final includes file.
execute_process(
        COMMAND npm run generate:include
        WORKING_DIRECTORY ${CMAKE_SOURCE_DIR}/sct
)

include_directories(inc)

file(GLOB_RECURSE SOURCE_FILES src/*.cc inc/*.hh sky/*.cc inc/*.h)
add_library(Exolix SHARED ${SOURCE_FILES})

add_subdirectory(exa)
