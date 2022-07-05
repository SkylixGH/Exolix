project(elixor)

add_library(elixor src/t.cxx)

target_include_directories(
    elixor
    PUBLIC include/   
)
