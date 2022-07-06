project(elixor)

file(
        GLOB SRC_FILES
        **/*.cxx
        **/*.hxx
        */**/*.cxx
        */**/*.hxx
)

add_library(elixor ${SRC_FILES})

target_include_directories(
    elixor
    PUBLIC ${CMAKE_SOURCE_DIR}
)
