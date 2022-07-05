project(elixor)

file(
        GLOB SRC_FILES
        src/*.cxx
        src/*.hxx
        src/**/*.cxx
        src/**/*.hxx
)

add_library(elixor ${SRC_FILES})

target_include_directories(
    elixor
    PUBLIC include/   
)

target_link_directories(
        elixor
        PUBLIC
        ${CMAKE_SOURCE_DIR}/deps/machinezone/IXWebSocket/ixwebsocket
)

target_include_directories(
        elixor
        PUBLIC
        ${CMAKE_SOURCE_DIR}/deps/machinezone/IXWebSocket/ixwebsocket
)
