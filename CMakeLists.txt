project(Skylix_Exolix)
cmake_minimum_required(VERSION 3.2)

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

# Make sure that at the start of every header file there is #pragma once
foreach(file ${Skylix_Exolix_INCLUDE_FILES})
    file(READ ${file} fileContent)
    string(FIND ${fileContent} "#pragma once" pragmaOnceFound)
    if(pragmaOnceFound EQUAL -1)
        message(FATAL_ERROR "Header file ${file} does not contain #pragma once")
    endif()
endforeach()

# Make sure that at the end of every file there is a newline character
foreach(file ${Skylix_Exolix_LIB_FILES} ${Skylix_Exolix_INCLUDE_FILES})
    file(READ ${file} fileContent)
    string(LENGTH ${fileContent} fileContentLength)
    math(EXPR fileContentLength "${fileContentLength} - 1")
    string(SUBSTRING ${fileContent} ${fileContentLength} 1 lastCharacter)
    if(NOT lastCharacter STREQUAL "\n")
        message(FATAL_ERROR "File ${file} does not end with a newline character")
    endif()
endforeach()

# Make sure that every file has "namespace exolix {" at line 3
foreach(file ${Skylix_Exolix_LIB_FILES} ${Skylix_Exolix_INCLUDE_FILES})
    file(READ ${file} fileContent)
    string(FIND ${fileContent} "namespace exolix {" namespaceExolixFound)
    if(namespaceExolixFound EQUAL -1)
        message(FATAL_ERROR "File ${file} does not contain \"namespace exolix {\"")
    endif()
endforeach()

# Add examples
file(GLOB SkylixExolix_EXAMPLES_LIST RELATIVE ${CMAKE_CURRENT_SOURCE_DIR}/example ${CMAKE_CURRENT_SOURCE_DIR}/example/*)
foreach(example ${SkylixExolix_EXAMPLES_LIST})
    message(STATUS "Exolix adding example: ${example}")
    add_subdirectory(example/${example})
endforeach()
