cmake_minimum_required(VERSION 3.0)

project(CPP_PROJECT)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/")

enable_language(Rust)
include(CMakeCargo)

include_directories(include)
add_subdirectory(rustl)

add_subdirectory(xl)
include_directories(xl/include)

add_executable(bin src/main.cxx)

target_link_libraries(bin SomeC)
target_include_directories(bin PUBLIC ${SomeC_INCLUDE_DIR})

if(WIN32)
    target_link_libraries(bin rustl ws2_32 userenv)
else()
    target_link_libraries(bin rustl dl pthread)
endif()
