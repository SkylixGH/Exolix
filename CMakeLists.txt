cmake_minimum_required(VERSION 3.20)
project(Exolix CXX)

include_directories(include)

set(CMAKE_CXX_STANDARD 20)

file(GLOB_RECURSE Exolix_SOURCE_FILES lib/*.cxx lib/*.hxx)
add_library(exolix STATIC ${Exolix_SOURCE_FILES})

macro(subdirlist result curdir)
    file(GLOB children RELATIVE ${curdir} ${curdir}/*)
    set(dirlist "")

    foreach(child ${children})
        if(IS_DIRECTORY ${curdir}/${child})
            LIST(APPEND dirlist ${child})
        endif()
    endforeach()

    set(${result} ${dirlist})
endmacro()

subdirlist(Test_DIRECTORIES ${CMAKE_SOURCE_DIR}/test)

foreach(subdir ${Test_DIRECTORIES})
    add_subdirectory(test/${subdir})
endforeach()
