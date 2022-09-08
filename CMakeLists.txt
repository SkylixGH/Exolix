cmake_minimum_required(VERSION 3.16)
project(Exolix CXX)

# Run include file generator and automatic
# file include assistance with guards.
execute_process(
        COMMAND node ${CMAKE_SOURCE_DIR}/gen/bin/cmake-assist-generator.js generate-include include/exolix.hxx lib
        WORKING_DIRECTORY ${CMAKE_SOURCE_DIR}
)

include_directories(include)

set(CMAKE_CXX_STANDARD 20)

file(GLOB_RECURSE Exolix_SOURCE_FILES lib/*.cxx lib/*.hxx)
add_library(exolix STATIC ${Exolix_SOURCE_FILES})

macro(subdirlist result curdir)
    file(GLOB children RELATIVE ${curdir} ${curdir}/*)
    set(dirlist "")

    foreach (child ${children})
        if (IS_DIRECTORY ${curdir}/${child})
            LIST(APPEND dirlist ${child})
        endif ()
    endforeach ()

    set(${result} ${dirlist})
endmacro()

# Add windows specific libraries
if (WIN32)
    target_link_libraries(exolix PUBLIC ws2_32)
endif ()

# Add OpenSSL libraries
find_package(OpenSSL REQUIRED)
target_link_libraries(exolix PUBLIC OpenSSL::SSL OpenSSL::Crypto)

subdirlist(Test_DIRECTORIES ${CMAKE_SOURCE_DIR}/test)

foreach (subdir ${Test_DIRECTORIES})
    add_subdirectory(test/${subdir})
endforeach ()
